package de.eneller.scripts;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class m3u8script {
    public static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        logger.setLevel(Level.ALL);
        if (args.length < 2) {
            printHelp();
            System.exit(1);
        }
        System.out.println(Arrays.toString(args));
        String url = "";
        String inputFile = "";
        String prefix = "media_";
        String output = "";
        boolean m = false;
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "-single":
                    url = args[i + 1].trim();
                    break;
                case "-multi":
                    inputFile = args[i + 1].trim();
                    m = true;
                    break;
                case "-prefix":
                    prefix = args[i + 1].trim();
                    break;
                case "-output":
                    output = args[i + 1].trim();
                    break;
            }
        }
        if (m == false) {
            if (output.matches(".*\\.m3u8$") == false) {
                output = output + "/";
            }
            parse(url, output, prefix);
        } else {
            String[] pathArr = output.split("/");

            if (output.matches(".*/$") == false) {
                output = output + "/";
            }

            try (Scanner scn = new Scanner(new File(inputFile));) {

                String data;
                int c = 0;
                int a = 0;
                while (scn.hasNextLine()) {
                    a++;
                    data = scn.nextLine();
                    if (parse(data, output, prefix)) {
                        c++;
                    }
                }
                logger.log(Level.INFO, c + " out of " + a + " operations successfully completed. In case of errors, please check the log.");
            } catch (FileNotFoundException e) {
                logger.log(Level.SEVERE, "Couldnt find input file");
            }
        }

    }

    public static boolean parse(String m3u8WebPath, String outputPath, String matchPrefix) {
        boolean b = true;
        try {
            URL url = new URL(m3u8WebPath);
            Scanner scn = new Scanner((new BufferedReader(new InputStreamReader(url.openStream()))));
            String[] splitWebPath = m3u8WebPath.split("/");
            String m3u8WebPrefix = "";
            for (int i = 0; i < splitWebPath.length - 1; i++) {
                m3u8WebPrefix = m3u8WebPrefix + splitWebPath[i] + "/";
            }
            if (outputPath.matches(".*/$")) {
                String generatedFileName = splitWebPath[splitWebPath.length - 2];
                generatedFileName = generatedFileName.replaceAll("%20", "_");
                generatedFileName = generatedFileName.replaceAll("\\.smil", ".m3u8");
                outputPath = outputPath + generatedFileName;
            }

            File outm3u8 = new File(outputPath);

            if (outm3u8.createNewFile()) {
                logger.log(Level.INFO, "File created at " + outputPath);
            } else {
                logger.log(Level.WARNING, "File already exists, please specify a different path " + outputPath);
                return false;
            }

            FileWriter outWriter = new FileWriter(outm3u8);
            String data = "";
            while (scn.hasNextLine()) {
                data = scn.nextLine();
                if (data.startsWith(matchPrefix)) {
                    data = m3u8WebPrefix + data;
                }
                outWriter.write(data + "\n");
            }
            outWriter.close();
            scn.close();
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Couldnt find .m3u8 file at specified path");
        } catch (MalformedURLException e) {
            logger.log(Level.SEVERE, "Couldnt read file at URL");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occured while trying to create the output file, please check that your path exists");
        }


        return true;
    }

    public static String getInput(String s) {
        Scanner scn = new Scanner(System.in);
        System.out.println(s);
        return scn.nextLine();
    }

    public static void printHelp() {
        System.out.println("Seems like you tried to run the script without specifying necessary parameters." + System.lineSeparator() +
                "The following commands are available:" + System.lineSeparator() + System.lineSeparator() +
                "Use -multi yourfile.txt to specify the absolute path to an input file containing a list of URLs" + System.lineSeparator() +
                "Or use -single urlto.m3u8 in combination with -output /path/to/output/folderorfile" + System.lineSeparator() + System.lineSeparator() +
                "Please go to https://github.com/eneller/m3u8script for additional help");
    }
}

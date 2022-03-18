package de.eneller.scripts;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Main{


    public static char[] matchArray = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
    public static int length = 6;

    public static void main(String[] args) throws IOException {
        String filepath = "https://www.mymuesli.com/";


        String data = "";
        CountArray append = new CountArray(length,matchArray.length-1);
        if(args.length>0){
            append.setArr(transform(args[0]));
        }
        String url = "";
        boolean b = true;


        do {
            b = true;
            url = filepath + buildString(append.getArr());
            if(testURL(url)){
                System.out.println(url);
            }
        }while(append.countUp()==true);



    }
    public static String buildString(int[] ints){
        String data = "";
        for(int i=0;i< ints.length;i++){
            data += matchArray[ints[i]];
        }
        return data;
    }
    public static boolean testURL(String urlstring) throws MalformedURLException, IOException {
        URL u = new URL(urlstring);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return (connection.getResponseCode()!=404);
    }

    public static int[] transform(String s){
        char[] c = s.toCharArray();
        int j;
        if(c.length==length){
            int[] n = new int[length];
            for(int i=0;i<length;i++){
                for(j=0; j< matchArray.length;j++){
                    if(matchArray[j]==c[i]){
                        break;
                    }
                }
                n[i]=j;
            }
            System.out.println(Arrays.toString(n));
            return n;
        }
        return new int[]{-1};
    }
}

public class CountArray {
    private int length;
    private int maxValue;
    private int[] arr;
    public CountArray(int length, int maxValue){
        this.length=length;
        this.maxValue=maxValue;
        this.arr = new int[length];
        for(int i=0;i<arr.length;i++){
            arr[i]=0;
        }
    }
    public boolean countUp(){
        int i =arr.length-1;
        if(arr[i]==maxValue){
            arr[i]=0;
            return countUp(i-1);
        }

        arr[i]++;
        return true;
    }
    public boolean countUp(int i){
        if(i==-1){
            return false;
        }
        if(arr[i]==maxValue){
            arr[i]=0;
            return countUp(i-1);
        }

        arr[i]++;
        return true;

    }

    public int[] getArr(){
        return this.arr;
    }

    public boolean setArr(int[] in){
        if(in.length==this.length){
            for(int i=0;i< length;i++){
                if(in[i]>maxValue){
                    return false;
                }
            }
            for(int i=0;i<length;i++){
                this.arr[i] = in[i];
            }
            return true;
        }
        return false;

    }
}

# m3u8script
A script to facilitate downloading videos saved as .m3u8 in general.

## Finding the .m3u8 file
[Please extract the URL to your .m3u8 file first:](findm3u8.mp4)
1. Open the developer tools
2. Go to the network tab and reload the page
3. locate an entry containing an m3u8 link
4. copy the link

In pictures:
![](find1.png)
![](find2.png)
(Pictures taken in Firefox)
</br></br>
![](brave.png)
(Pictures taken in Brave (Chromium Browser))

## Using the tool
The tool does not need to be installed, only downloaded (m3u8script.java). All you might need to do is [install the Java Development Kit, or JDK,](https://www.oracle.com/de/java/technologies/javase-jdk15-downloads.html) and set the CLASSPATH variable.
You can then run it:
![java m3u8script.java](cli.jpg)

When encountering an error, you might also try 
`java -cp ./ m3u8script.java`

## Options
|option|explanation|
|----|----|
|`-single m3u8URL`| allows you to download and convert a single .m3u8 file. In this case, output must either end in a *folder/* or *file.m3u8*|
|`-multi textfile`| allows you to specify the path to a text file that contains links to .m3u8 files (one on each line), allowing hands-free batch processing. -output must specify a folder path|
|`-output fileorfolder`| specifies a local directory path. When using ’-multi’, it must contain only a folder name, since filenames will be applied automatically. When used with -single, it can contain a full filename with the extension .m3u8. If you want to use automatic naming, just specify a folder path|
|`-prefix prefixToUse`| You can use this command to specify the pattern this script should look for. Default should work in most cases. |

Example: `java m3u8script.java -multi /home/guest/Downloads/list.txt -output /home/guest/Downloads/videos`

## Downloading
You can then use ffmpeg or VLC media player to download the videos:

![vlc](vlc.png)

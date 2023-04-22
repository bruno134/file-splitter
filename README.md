# file-splitter
This Java project breaks a large file into smaller files, based on predefined headers and traillers.

### **WARNING**: This version only works when all lines of the file has same size **(FIXED BLOCK)**

## Requirements
To use this application, you need have installed Java 11 or higher on your machine.

## How to use

Given a file with following content

```
@MYHEADERFILE
CONTENTINFILE
CONTENTINFILE
9FOOFOOTFOOTF
@MYHEADERFILE
CONTENTINFILE
9FOOFOOTFOOTF
```
1 - First you need to create and setup the markers of your file.


```java
Map<String, MarkerEnum> markers = new HashMap<>();
markers.put("@", MarkerEnum.HEADER);
markers.put("9", MarkerEnum.TRAILLER);
```



2 - Then create and setup the configuration.

```java
SplitterFileConfiguration config = SplitterFileConfiguration.builder()
				   .withMarkers(markers)
				   .withFileConfiguration(FileConfiguration.builder()
								.withFileName(<your source file>).build())
								.build();
```

3 - And finally Instantiate a Splitter object and call the ``splitAndWrite`` method.

```java
new Splitter(config).splitAndWrite(sourceFilename, destinationPath);
```
4 - The result is two splitted file, available in informed destination path

File 1 content:

```
@MYHEADERFILE
CONTENTINFILE
CONTENTINFILE
9FOOFOOTFOOTF

```

File 2 content:

```
@MYHEADERFILE
CONTENTINFILE
9FOOFOOTFOOTF
```

## Configuration Class
``withMarkers(Map<String, MarkerEnum> markers)`` - Define what are the file markers that represents a unique file result.

``withOutputFileName(String outputFileName)`` - Define the name of resulting output file(s). Default name is "splitted_file"

``withOutputFileExtension(String outputFileExtension)`` - Define the extension of resulting output file(s). Default extension is ".txt"

``withThreadPool(Integer threadPool)`` - Define how many threads are going to be started at the process
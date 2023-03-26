# file-splitter
This Java project breaks a large file into smaller files, based on predefined headers and traillers.

## Requirements
To use this application, you need have installed Java 11 or higher on your machine.

## How to use

1 - First you need to create and setup the markers of your file.


```java
Map<String, MarkerEnum> markers = new HashMap<>();
markers.put("@", MarkerEnum.HEADER);
markers.put("9", MarkerEnum.TRAILLER);
```



2 - Then we need create and  setup our configuration.

```java
SplitterFileConfiguration config = SplitterFileConfiguration.builder()
											.withMarkers(markers)
											.withFileConfiguration(FileConfiguration.builder()
													.withFileName(<your source file>)
													.build())
											.withThreadPool(5)
											.withMonitor(true)
											.build();
```

3 - And finally Instantiate a Splitter object and call the ``splitAndWrite`` method.

```java
new Splitter(config).splitAndWrite(sourceFilename, destinationPath);
```

## Configuration Class
``withMarkers(Map<String, MarkerEnum> markers)`` - Define what are the file markers that represents a unique file result.
``withThreadPool(Integer threadPool)`` - Define how many threads are going to be started at the process
``withMonitor(boolean monitor)`` Enable a monitor that identifies when all the tasks are finished and terminates the main process
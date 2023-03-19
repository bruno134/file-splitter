package br.com.fileSplitter.file.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomBlockFileReader {

	private static final String READ = "r";

	public StringBuilder readBlock(String originalFilename, Integer lineLength, Integer firstPointer,
			Integer lastPointer) throws IOException, RandomBlockFileReaderException {

		if (originalFilename == null) {
			throw new RandomBlockFileReaderException("Original file can't be null");
		}

		File originalFile = new File(originalFilename);

		return readBlock(originalFile, lineLength, firstPointer, lastPointer);

	}

	public StringBuilder readBlock(File originalFile, Integer lineLength, Integer firstPointer, Integer lastPointer)
			throws IOException, RandomBlockFileReaderException {

		if (originalFile == null) {
			throw new RandomBlockFileReaderException("Original file can't be null");
		}
		
		if (!originalFile.exists()) {
			throw new FileNotFoundException(originalFile.getAbsolutePath() + " was not found in the given path");
		}
		
		if(!originalFile.canRead()) {
			throw new FileNotFoundException("Permission denied to read " + originalFile.getAbsolutePath());
		}
		
		if(firstPointer==null | lastPointer==null | lineLength==null) {
			throw new RandomBlockFileReaderException("firstPointer|lastPointer|lineLength can't be null");
		}

		StringBuilder contentFile = new StringBuilder();

		try (RandomAccessFile reader = new RandomAccessFile(originalFile, READ)) {

			int position = firstPointer * (lineLength + 1);
			String contentOfLine = "";

			for (int pointer = firstPointer; pointer <= lastPointer; pointer++) {
				reader.seek(position);
				contentOfLine = reader.readLine();
				contentFile.append(contentOfLine);
				contentFile.append("\n");

				position += contentOfLine.length() + 1;
			}
		};

		return contentFile;
	}

}

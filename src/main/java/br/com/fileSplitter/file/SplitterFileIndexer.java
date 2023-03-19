package br.com.fileSplitter.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class SplitterFileIndexer {

	// TODO allow customize header and trailler mark
	private static final String READ = "r";

	public Map<Integer, int[]> mapIndexes(String sourceFileName) throws SplitterFileIndexerException, IOException {
		if (sourceFileName == null) {
			throw new SplitterFileIndexerException("Source file can't be null");
		}

		File sourceFile = new File(sourceFileName);

		return mapIndexes(sourceFile);
	}

	public Map<Integer, int[]> mapIndexes(File sourceFile) throws SplitterFileIndexerException, IOException {

		fileValidate(sourceFile);
		
		Map<Integer, int[]> mapPointers = new HashMap<>();
		int charPosition = 0;
		int group = 1;
		int linePosition = 1;
		byte charReaded = 0;
		int[] indexes = new int[2];

		try (RandomAccessFile reader = new RandomAccessFile(sourceFile, READ)) {

			reader.seek(charPosition);
			var firstLineAux = reader.readLine();
			var lineSize = firstLineAux.length()+1;
			charReaded = (byte) firstLineAux.charAt(0);

			while (charReaded >= 0) {

				if ((char) charReaded == '@') {
					indexes[0] = linePosition;
					
				} else if ((char) charReaded == '9') {			
					indexes[1] = linePosition;
				}
				
				if(indexes[0]>0&&indexes[1]>0) {
					int[] pointerValues = {indexes[0],indexes[1]};
					mapPointers.put(group, pointerValues);
					group++;
					indexes[0]=0;
					indexes[1]=0;
				}

				reader.seek(charPosition+=lineSize);
				charReaded = (byte) reader.read();
				linePosition++;

			}

		}

		return mapPointers;
	}
	
	private void fileValidate(File sourceFile) throws SplitterFileIndexerException, FileNotFoundException {
		if (sourceFile == null) {
			throw new SplitterFileIndexerException("Source file can't be null");
		}

		if (!sourceFile.exists()) {
			throw new FileNotFoundException(sourceFile.getAbsolutePath() + " was not found in the given path");
		}

		if (!sourceFile.canRead()) {
			throw new FileNotFoundException("Permission denied to read " + sourceFile.getAbsolutePath());
		}
	}
	

}

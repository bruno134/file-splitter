package br.com.fileSplitter.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitterFileIndexer {

	// TODO allow customize header and trailler mark
	private static final String READ = "r";

	public List<Index> mapIndexes(String sourceFileName) throws SplitterFileException {
		if (sourceFileName == null) {
			throw new SplitterFileException("Source file can't be null");
		}

		File sourceFile = new File(sourceFileName);

		return mapIndexes(sourceFile);
	}

	public List<Index> mapIndexes(File sourceFile) throws SplitterFileException {

		fileValidate(sourceFile);
		
		List<Index> indexes = new ArrayList<>();
		int pointerPosition = 0;
		int linePosition = 1;
		byte charReaded = 0;
		int[] indexHeader   = new int[2];
		int[] indexTrailler = new int[2];

		try (RandomAccessFile reader = new RandomAccessFile(sourceFile, READ)) {

			reader.seek(pointerPosition);
			var firstLineAux = reader.readLine();
			var lineSize = firstLineAux.length()+0;
			charReaded = (byte) firstLineAux.charAt(0);

			
			
			while (charReaded >= 0) {

				//In case of EOL is composed by CR+CF (windows)
				if(charReaded==10||charReaded==13) {
					pointerPosition++;
					reader.seek(pointerPosition);
					charReaded = (byte)reader.read();
					continue;
				}
				
				if ((char) charReaded == '@') {
			//TODO improve the code
					indexHeader[0] = linePosition;
					indexHeader[1] = pointerPosition;
					
				} else if ((char) charReaded == '9') {			
					indexTrailler[0] = linePosition;
					indexTrailler[1] = pointerPosition;
				}
				
				if(indexHeader[0]>0&&indexTrailler[0]>0) {
					
					indexes.add(new Index(new Pointer(indexHeader[0], indexHeader[1]), 
							    new Pointer(indexTrailler[0], indexTrailler[1])));
					
					indexHeader[0]  =0;
					indexHeader[1]  =0;
					indexTrailler[0]=0;
					indexTrailler[1]=0;
				}

				reader.seek(pointerPosition+=lineSize);
				charReaded = (byte) reader.read();
				linePosition++;

			}

		} catch (FileNotFoundException e) {
			throw new SplitterFileException(e.getMessage());
		} catch (IOException e) {
			throw new SplitterFileException(e.getMessage());
		}

		return indexes;
	}
	
	private void fileValidate(File sourceFile) throws SplitterFileException {
		if (sourceFile == null) {
			throw new SplitterFileException("Source file can't be null");
		}

		if (!sourceFile.exists()) {
			throw new SplitterFileException(sourceFile.getAbsolutePath() + " was not found in the given path");
		}

		if (!sourceFile.canRead()) {
			throw new SplitterFileException("Permission denied to read " + sourceFile.getAbsolutePath());
		}
	}
	

}

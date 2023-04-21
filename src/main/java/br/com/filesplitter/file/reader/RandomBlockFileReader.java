package br.com.filesplitter.file.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import br.com.filesplitter.file.SplitterFileException;
import br.com.filesplitter.file.model.Index;

public class RandomBlockFileReader implements SplitterReader{

	private static final String READ = "r";	
	private final File sourceFile;
	
	public RandomBlockFileReader(File sourceFile) {		
		this.sourceFile = sourceFile;
	}

	@Override
	public StringBuilder read(Index index) throws SplitterFileException {
		
		if (sourceFile==null) {
			throw new SplitterFileException("Source file cannot be null");
		}
		
		if (!sourceFile.exists()) {
			throw new SplitterFileException(sourceFile.getAbsolutePath() + " was not found in the given path");
		}
		
		if(!sourceFile.canRead()) {
			throw new SplitterFileException("Permission denied to read " + sourceFile.getAbsolutePath());
		}
		
		if(index==null)
			throw new SplitterFileException("Index was not provided");
		
		StringBuilder contentFile = new StringBuilder();

		try (RandomAccessFile reader = new RandomAccessFile(sourceFile, READ)) {
			
			int position = index.getHeader().getPointerPosition();
			String contentOfLine = "";			
			
			for (int pointer = index.getHeader().getLinePosition(); pointer <= index.getTrailler().getLinePosition(); pointer++) {
				
				reader.seek(position);
				contentOfLine = reader.readLine();
				contentFile.append(contentOfLine);
				contentFile.append("\n");				
				
				position += contentOfLine.length() + 1;
			}
			
		} catch (IOException e) {
			throw new SplitterFileException(e.getMessage());
		}

		return contentFile;
		
	}

}
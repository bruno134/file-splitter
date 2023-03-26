package br.com.fileSplitter.file.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import br.com.fileSplitter.file.SplitterFileException;
import br.com.fileSplitter.file.model.Index;

public class RandomBlockFileReader implements SplitterReader{

	private static final String READ = "r";	
	private final File sourceFile;
	
	//TODO esta com cara de que tenho race Condition aqui.
	public RandomBlockFileReader(File sourceFile) {		
		this.sourceFile = sourceFile;
	}


	public RandomBlockFileReader(String sourceFile) {	
		File file = new File(sourceFile);
		this.sourceFile = file;
	}


	@Override
	public StringBuilder read(Index index) throws SplitterFileException {
				
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
			
		} catch (FileNotFoundException e) {
			throw new SplitterFileException(e.getMessage());
		} catch (IOException e) {
			throw new SplitterFileException(e.getMessage());
		};

		return contentFile;
		
	}

}

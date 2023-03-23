package br.com.fileSplitter.file.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fileSplitter.file.Index;
import br.com.fileSplitter.file.SplitterFileConfiguration;
import br.com.fileSplitter.file.SplitterFileException;

public class RandomBlockFileReader implements SplitterReader{

	private static final String READ = "r";
	private static final Logger LOG = LoggerFactory.getLogger(RandomBlockFileReader.class);
	
	
	@Override
	public StringBuilder read(Index index, String sourceFile) throws SplitterFileException {
		
		File file = new File(sourceFile);
		
		if (!file.exists()) {
			throw new SplitterFileException(file.getAbsolutePath() + " was not found in the given path");
		}
		
		if(!file.canRead()) {
			throw new SplitterFileException("Permission denied to read " + file.getAbsolutePath());
		}
		
		if(index==null)
			throw new SplitterFileException("Index was not provided");
		
		StringBuilder contentFile = new StringBuilder();

		try (RandomAccessFile reader = new RandomAccessFile(file, READ)) {
			
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

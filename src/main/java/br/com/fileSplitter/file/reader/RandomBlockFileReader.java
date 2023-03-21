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
	public StringBuilder read(Index index, SplitterFileConfiguration configuration) throws SplitterFileException {

		if(configuration==null)
			throw new SplitterFileException("Configuration not set");
		
		if("".equals(configuration.getSourceFile()))
			throw new SplitterFileException("Source file name not set");
		
		File sourceFile = new File(configuration.getSourceFile());
		
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

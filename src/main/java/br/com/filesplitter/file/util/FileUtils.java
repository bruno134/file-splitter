package br.com.filesplitter.file.util;

import java.io.File;
import java.io.IOException;

import br.com.filesplitter.file.SplitterFileException;

public class FileUtils {

	private FileUtils() {
		
	}
	
	public static void fileValidate(File sourceFile) throws SplitterFileException {
		if (sourceFile == null) {
			throw new SplitterFileException("Source file can't be null");
		}

		if (!sourceFile.exists()) {
			throw new SplitterFileException(sourceFile.getAbsolutePath() + " was not found in the given path");
		}
		
		if(!sourceFile.canRead()) {
			throw new SplitterFileException("Can't read the file " + sourceFile.getAbsolutePath());
		}
		
	}
	
	public static void fileValidate(String sourceFile) throws SplitterFileException {
		if (sourceFile == null) {
			throw new SplitterFileException("Source file can't be null");
		}

		if ("".equals(sourceFile)) {
			throw new SplitterFileException("Source file can't be blank");
		}
		
		fileValidate(new File(sourceFile));
		
	}
	
	public static File createFile(String fileName) throws SplitterFileException {

		File file = null;

		try {		

			if ("".equals(fileName)) {
				throw new SplitterFileException("A File or filename must be provided");
			}
						
			file = new File(fileName);	
			
			File newDirectory = new File(file.getParent());

			// Try to create a folder if it doesn't exists
			if (!newDirectory.exists()) {
				 newDirectory.mkdirs();
			}
			
			if(!file.createNewFile()) {
				throw new SplitterFileException("Could not create file");
			}			
		
		} catch (IOException | NullPointerException e) {
			throw new SplitterFileException("A File or filename must be provided");
		}

		return file;
	}
	

	
	public static String returnFilePath(String file) throws SplitterFileException{	
		
		if(file==null || "".equals(file)) {
			throw new SplitterFileException("A File or filename must be provided");
		}
		
		return new File(file).getParent();
	}
	
	
	
}

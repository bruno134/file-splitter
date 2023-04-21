package br.com.filesplitter.file.util;

import java.io.File;
import java.io.IOException;

import br.com.filesplitter.file.SplitterFileException;

public class FileUtils {

	
	public static void fileValidate(File sourceFile) throws SplitterFileException {
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

				if (!newDirectory.mkdirs()) {
					throw new SplitterFileException("Could not create path or path invalid");
				}
				file.createNewFile();
			}
		} catch (IOException e) {
			throw new SplitterFileException("Could not create path or path invalid");
		} catch (NullPointerException e) {
			throw new SplitterFileException("A File or filename must be provided");
		}

		return file;
	}
	

	
	public static String returnFilePath(String file) {		
		return new File(file).getParent();
	}
	
	
	
}
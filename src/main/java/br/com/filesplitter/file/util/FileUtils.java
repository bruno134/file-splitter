package br.com.filesplitter.file.util;

import java.io.File;
import java.io.IOException;

import br.com.filesplitter.file.SplitterFileException;

public class FileUtils {

	public static final String A_FILE_OR_FILENAME_MUST_BE_PROVIDED = "A File or filename must be provided";
	public static final String SOURCE_FILE_CAN_T_BE_BLANK = "Source file can't be blank";
	public static final String CAN_T_READ_THE_FILE = "Can't read the file ";
	public static final String WAS_NOT_FOUND_IN_THE_GIVEN_PATH = " was not found in the given path";
	public static final String SOURCE_FILE_CAN_T_BE_NULL = "Source file can't be null";



	private FileUtils() {
		
	}
	
	public static void fileValidate(File sourceFile) throws SplitterFileException {
		if (sourceFile == null) {
			throw new SplitterFileException(SOURCE_FILE_CAN_T_BE_NULL);
		}

		if (!sourceFile.exists()) {
			throw new SplitterFileException(sourceFile.getAbsolutePath() + WAS_NOT_FOUND_IN_THE_GIVEN_PATH);
		}
		
		if(!sourceFile.canRead()) {
			throw new SplitterFileException(CAN_T_READ_THE_FILE + sourceFile.getAbsolutePath());
		}
		
	}
	
	public static void fileValidate(String sourceFile) throws SplitterFileException {
		if (sourceFile == null) {
			throw new SplitterFileException(SOURCE_FILE_CAN_T_BE_NULL);
		}

		if ("".equals(sourceFile)) {
			throw new SplitterFileException(SOURCE_FILE_CAN_T_BE_BLANK);
		}
		
		fileValidate(new File(sourceFile));
		
	}
	
	public static File createFile(String fileName) throws SplitterFileException {

		File file = null;

		try {		

			if ("".equals(fileName)) {
				throw new SplitterFileException(A_FILE_OR_FILENAME_MUST_BE_PROVIDED);
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
			throw new SplitterFileException(A_FILE_OR_FILENAME_MUST_BE_PROVIDED);
		}

		return file;
	}
	

	
	public static String returnFilePath(String file) throws SplitterFileException{	
		
		if(file==null || "".equals(file)) {
			throw new SplitterFileException(A_FILE_OR_FILENAME_MUST_BE_PROVIDED);
		}
		
		return new File(file).getParent();
	}
	
	
	
}

package br.com.fileSplitter.file.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SplitterFileWriter {

	// private static String FILE_PATH = "src//main//out//";
	String FILE_PATH = "src//main//out//";

	public String writeAndSaveFile(String fileName, StringBuilder stringBuilder, Integer firstLine, Integer lastLine)
			throws FileWriterException {

		File file = createFile(fileName);

		return writeAndSaveFile(file, stringBuilder, firstLine, lastLine);
	}

	public String writeAndSaveFile(StringBuilder stringBuilder, Integer firstLine, Integer lastLine)
			throws FileWriterException {

		//TODO Where should the default path be when file name is not given?

		final String fileName = String.format("File_%d%d_%d.txt", firstLine, lastLine, System.currentTimeMillis());

		File file = createFile(FILE_PATH + fileName);

		return writeAndSaveFile(file, stringBuilder, firstLine, lastLine);

	}

	public String writeAndSaveFile(File file, StringBuilder stringBuilder, Integer firstLine, Integer lastLine) throws FileWriterException {

		// TODO possibilitar informar extensão desejada
		final String errorMessage;
		
		//TODO Melhorar validações
		if((errorMessage= validateInputs(file, stringBuilder, firstLine, lastLine))!=null)
			throw new FileWriterException(errorMessage);
		
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
			writer.write(stringBuilder.toString());			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file.getName();
	}

	private File createFile(String fileName) throws FileWriterException {

		File file = null;
		
		try {
			file = new File(fileName);	
			File newDirectory = new File(file.getParent());
			
			//Try to create a folder if it doesn't exists
			if(!newDirectory.exists()) {
				
				if(!newDirectory.mkdirs()) {
					throw new FileWriterException("Could not create path or path invalid");
				}
				file.createNewFile();
			}			
		} catch (IOException e) {
			throw new FileWriterException("Could not create path or path invalid");
		} catch (NullPointerException e) {
			throw new FileWriterException("A File or filename must be provided");
		}

		return file;
	}
	
	
	private String validateInputs(File file, StringBuilder stringBuilder, Integer firstLine, Integer lastLine) {
		
				
		if(stringBuilder==null)
			return "StringBuilder cannot be null";			
		
		if(firstLine == null | lastLine ==null)
			return "First/Last line of file must be provided";			
		
		if(file==null)
			return "File cannot be null";
		
		return null;
				
	}

}

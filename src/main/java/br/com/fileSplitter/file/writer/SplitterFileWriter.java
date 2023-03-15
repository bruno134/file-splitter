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
		
		if(stringBuilder==null)
			throw new FileWriterException("StringBuilder cannot be null");
		
		if(firstLine == null | lastLine ==null)
			throw new FileWriterException("First/Last line of file must be provided");
		
		if(file==null)
			throw new FileWriterException("File cannot be null");
		
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
			writer.write(stringBuilder.toString());			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file.getName();
	}

	public File createFile(String fileName) throws FileWriterException {

		File file = null;

		try {
			file = new File(fileName);
			file.createNewFile();

		} catch (IOException e) {
			throw new FileWriterException("File path not found or invalid");
		} catch (NullPointerException e) {
			throw new FileWriterException("A File must be provided");
		}

		return file;
	}

}

package br.com.fileSplitter.file.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.fileSplitter.file.SplitterFileConfiguration;
import br.com.fileSplitter.file.SplitterFileException;

public class SplitterFileWriter implements SplitterWriter{


	@Override
	public void write(StringBuilder input, SplitterFileConfiguration configuration) throws SplitterFileException {

		final String errorMessage;

		if ((errorMessage = validateInputs(input, configuration)) != null)
			throw new SplitterFileException(errorMessage);

		File file = createFile(configuration);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(input.toString());
		} catch (IOException e) {
			throw new SplitterFileException(e.getMessage());
		}

	}
	

	private File createFile(SplitterFileConfiguration configuration) throws SplitterFileException {

		File file = null;

		try {

			if ("".equals(configuration.getFileConfiguration().getFilePath())) {
				throw new SplitterFileException("File path can not be blank");
			}

			if ("".equals(configuration.getFileConfiguration().getFileName())) {
				throw new SplitterFileException("A File or filename must be provided");
			}
			
			
			String fullFileName = configuration.getFileConfiguration().getFilePath() +
								  configuration.getFileConfiguration().getFileName();
			
			file = new File(fullFileName + System.currentTimeMillis() + 
						    configuration.getFileConfiguration().getFileExtension());	
			
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

	private String validateInputs(StringBuilder stringBuilder, SplitterFileConfiguration configuration) {

		if (configuration == null)
			return "Configuration not set";

		if (stringBuilder == null)
			return "StringBuilder cannot be null";

		if (configuration.getFileConfiguration() == null)
			return "File configuration not set";

		return null;

	}
	
	

	// private static String FILE_PATH = "src//main//out//";
//	String FILE_PATH = "resultFiles/";
	
//	public String writeAndSaveFile(String fileName, String destinationPath, StringBuilder stringBuilder, Integer firstLine, Integer lastLine)
//			throws FileWriterException {
//
//		String filePath;
//		
//		if("".equals(destinationPath))
//			filePath = FILE_PATH;
//		else 
//			filePath = destinationPath;
//		
//		//TODO Segment the creation of file. Create separated functions.
//		File file = createFile(filePath,fileName, firstLine, lastLine);
//
//		return writeAndSaveFile(file, stringBuilder, firstLine, lastLine);
//	}
//
//	public String writeAndSaveFile(StringBuilder stringBuilder, Integer firstLine, Integer lastLine)
//			throws FileWriterException {
//
//		//TODO Where should the default path be when file name is not given?
//
//		final String fileName = "File.txt";
//
//		File file = createFile(FILE_PATH , fileName, firstLine, lastLine);
//
//		return writeAndSaveFile(file, stringBuilder, firstLine, lastLine);
//
//	}

//	public String writeAndSaveFile(File file, StringBuilder stringBuilder, Integer firstLine, Integer lastLine) throws FileWriterException {
//
//		
//		// TODO possibilitar informar extensão desejada
//		final String errorMessage;
//		
//		//TODO Melhorar validações
//		if((errorMessage= validateInputs(file, stringBuilder, firstLine, lastLine))!=null)
//			throw new FileWriterException(errorMessage);
//		
//		
//		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
//			writer.write(stringBuilder.toString());			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return file.getName();
//	}

}

package br.com.fileSplitter.file.writer;

import java.io.File;

public class SplitterFileWriter {

	// private static String FILE_PATH = "src//main//out//";
	String FILE_PATH = "src//main//out//";

	public String writeAndSaveFile(File file, StringBuilder stringBuilder, Integer firstLine, Integer lastLine) {

		
		// TODO possibilitar informar extensão desejada
		
		return null;
	}

	public String writeAndSaveFile(String fileName, StringBuilder stringBuilder, Integer firstLine, Integer lastLine) {
		
		File file = createFile(fileName);

		return writeAndSaveFile(file, stringBuilder, firstLine, lastLine);
	}

	public String writeAndSaveFile(StringBuilder stringBuilder, Integer firstLine, Integer lastLine) {

		final String fileName = String.format("File_%d%d_%d.txt", firstLine, lastLine, System.currentTimeMillis());
		
		File file = createFile(fileName);
		
		return writeAndSaveFile(file, stringBuilder, firstLine, lastLine);
		
	}

	public File createFile(String fileName) {

		File file =  new File(fileName);
		
		return null;
	}

}

	package br.com.filesplitter.file.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.filesplitter.file.SplitterFileException;

public class SplitterFileWriter implements SplitterWriter{
	
	public static final String TARGET_FILE_NOT_PROVIDED = "Target File not provided";
	public static final String STRING_BUILDER_CANNOT_BE_NULL = "StringBuilder cannot be null";
	private File targetFile;
	
	
	public SplitterFileWriter(File targetFile) {
		super();
		this.targetFile = targetFile;
	}

	@Override
	public void write(StringBuilder input) throws SplitterFileException {

		final String errorMessage;

		if ((errorMessage = validateInputs(input)) != null)
			throw new SplitterFileException(errorMessage);
			
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
			writer.write(input.toString());
		} catch (IOException e) {
			throw new SplitterFileException(e.getMessage());
		}

	}
	
	private String validateInputs(StringBuilder stringBuilder) {

		if (targetFile == null)
			return TARGET_FILE_NOT_PROVIDED;

		if (stringBuilder == null)
			return STRING_BUILDER_CANNOT_BE_NULL;

		return null;

	}
}

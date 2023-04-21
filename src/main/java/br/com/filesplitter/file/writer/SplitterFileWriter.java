	package br.com.filesplitter.file.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.filesplitter.file.SplitterFileException;

public class SplitterFileWriter implements SplitterWriter{
	
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
			return "Target File not provided";

		if (stringBuilder == null)
			return "StringBuilder cannot be null";

		return null;

	}
}
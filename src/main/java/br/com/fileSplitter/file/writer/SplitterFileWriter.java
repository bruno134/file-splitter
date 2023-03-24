	package br.com.fileSplitter.file.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.fileSplitter.file.FileUtils;
import br.com.fileSplitter.file.SplitterFileConfiguration;
import br.com.fileSplitter.file.SplitterFileException;

public class SplitterFileWriter implements SplitterWriter{

	private final SplitterFileConfiguration configuration;
	
	

	public SplitterFileWriter(SplitterFileConfiguration configuration) {
		super();
		this.configuration = configuration;
	}


	@Override
	public void write(StringBuilder input, Integer fileNumber) throws SplitterFileException {

		final String errorMessage;

		if ((errorMessage = validateInputs(input, configuration)) != null)
			throw new SplitterFileException(errorMessage);

		File file = createFile(configuration, fileNumber);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(input.toString());
		} catch (IOException e) {
			throw new SplitterFileException(e.getMessage());
		}

	}
	

	private File createFile(SplitterFileConfiguration configuration, Integer fileNumber) throws SplitterFileException {
		
		 return  FileUtils.createFile(configuration.getFileConfiguration().getFileName()+
				 																	"_" +
				 															  fileNumber+
				                 configuration.getFileConfiguration().getFileExtension());

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
}

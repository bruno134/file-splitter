package br.com.fileSplitter.file.writer;

import br.com.fileSplitter.file.SplitterFileConfiguration;
import br.com.fileSplitter.file.SplitterFileException;

public interface SplitterWriter {

	
	public void write(StringBuilder input, SplitterFileConfiguration configuration) throws SplitterFileException;
}

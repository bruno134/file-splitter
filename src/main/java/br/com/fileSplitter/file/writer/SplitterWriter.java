package br.com.fileSplitter.file.writer;

import br.com.fileSplitter.file.SplitterFileException;

public interface SplitterWriter {

	
	public void write(StringBuilder input, Integer orderNumber) throws SplitterFileException;
}

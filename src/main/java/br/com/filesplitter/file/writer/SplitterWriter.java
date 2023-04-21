package br.com.filesplitter.file.writer;

import br.com.filesplitter.file.SplitterFileException;

public interface SplitterWriter {

	
	public void write(StringBuilder input) throws SplitterFileException;
}

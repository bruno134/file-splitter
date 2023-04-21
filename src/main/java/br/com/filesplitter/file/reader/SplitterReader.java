package br.com.filesplitter.file.reader;

import br.com.filesplitter.file.SplitterFileException;
import br.com.filesplitter.file.model.Index;

public interface SplitterReader {
	
	
	public StringBuilder read(Index index) throws SplitterFileException;

}

package br.com.fileSplitter.file.reader;

import br.com.fileSplitter.file.SplitterFileException;
import br.com.fileSplitter.file.model.Index;

public interface SplitterReader {
	
	
	public StringBuilder read(Index index) throws SplitterFileException;

}

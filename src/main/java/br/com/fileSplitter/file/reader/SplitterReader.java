package br.com.fileSplitter.file.reader;

import br.com.fileSplitter.file.Index;
import br.com.fileSplitter.file.SplitterFileConfiguration;
import br.com.fileSplitter.file.SplitterFileException;

public interface SplitterReader {
	
	
	public StringBuilder read(Index index, SplitterFileConfiguration configuration) throws SplitterFileException;

}

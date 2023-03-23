package br.com.fileSplitter.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class SplitterFileIndexerTest {

	private String sourceFilename = "src/test/resources/XS-test-file-1000.txt";
	private String sourceFilenameRestricted = "src/test/resources/test-file-restrict.txt";
	private String sourceFilenameNoHeaderTrailler = "src/test/resources/XS-test-file-no-header-trailler.txt";
	private String sourceFilenameNoHeader = "src/test/resources/XS-test-file-1000-no-header.txt";
	private String sourceFilenameNoTrailler = "src/test/resources/XS-test-file-1000-no-trailler.txt";
	private File sourceFile = new File(sourceFilename);
	//private SplitterFileIndexer indexer = new SplitterFileIndexer();

//	@Test
//	void shouldFindAllIndexesInGivenFileName() throws SplitterFileIndexerException, IOException {
//
//		var ret = indexer.mapIndexes(sourceFilename);
//
//		assertEquals(4, ret.size());
//		assertEquals(1, ret.get(1)[0]);
//		assertEquals(10, ret.get(1)[1]);
//		assertEquals(11, ret.get(2)[0]);
//		assertEquals(17, ret.get(2)[1]);
//		assertEquals(18, ret.get(3)[0]);
//		assertEquals(22, ret.get(3)[1]);
//		assertEquals(23, ret.get(4)[0]);
//		assertEquals(31, ret.get(4)[1]);
//
//	}
//
//	@Test
//	void shouldFindAllIndexesInGivenFile() throws SplitterFileIndexerException, IOException {
//
//		var ret = indexer.mapIndexes(sourceFile);
//
//		assertEquals(4, ret.size());
//		assertEquals(1, ret.get(1)[0]);
//		assertEquals(10, ret.get(1)[1]);
//		assertEquals(11, ret.get(2)[0]);
//		assertEquals(17, ret.get(2)[1]);
//		assertEquals(18, ret.get(3)[0]);
//		assertEquals(22, ret.get(3)[1]);
//		assertEquals(23, ret.get(4)[0]);
//		assertEquals(31, ret.get(4)[1]);
//
//	}
//	
//	@Test
//	void shouldFindNoIndexWhenFileDoNotHaveHeaderAndTrailler() throws SplitterFileIndexerException, IOException {
//
//		var ret = indexer.mapIndexes(sourceFilenameNoHeaderTrailler);
//
//		assertEquals(0, ret.size());
//
//	}
//	
//	@Test
//	void shouldFindNoIndexWhenFileDoNotHaveHeader() throws SplitterFileIndexerException, IOException {
//
//		var ret = indexer.mapIndexes(sourceFilenameNoHeader);
//		
//		assertEquals(0, ret.size());
//
//	}
//	
//	@Test
//	void shouldFindNoIndexWhenFileDoNotHaveTrailler() throws SplitterFileIndexerException, IOException {
//
//		var ret = indexer.mapIndexes(sourceFilenameNoTrailler);
//		
//		assertEquals(0, ret.size());
//
//	}
//
//	@Test
//	void shouldRaiseErrorWhenFileNameIsNotGiven() {
//
//		IOException ioException = assertThrows(IOException.class, () -> {
//
//			String sourceFileName = "";
//
//			indexer.mapIndexes(sourceFileName);
//		});
//
//		assertTrue(ioException.getMessage().contains(" was not found in the given path"));
//
//	}
//
//	@Test
//	void shouldRaiseErrorWhenFileNameIsNull() {
//
//		String filename = null;
//
//		SplitterFileIndexerException indexerException = assertThrows(SplitterFileIndexerException.class, () -> {
//
//			indexer.mapIndexes(filename);
//		});
//
//		assertTrue(indexerException.getMessage().equals("Source file can't be null"));
//
//	}
//
//	@Test
//	void shouldRaiseErrorWhenFileIsNull() {
//
//		File file = null;
//
//		SplitterFileIndexerException indexerException = assertThrows(SplitterFileIndexerException.class, () -> {
//
//			indexer.mapIndexes(file);
//		});
//
//		assertTrue(indexerException.getMessage().equals("Source file can't be null"));
//
//	}
//
//	@Test
//	void shouldRaiseErrorWhenGivenFileIsNotFound() {
//		String filename = "UnfindbleFile.txt";
//
//		IOException ioException = assertThrows(IOException.class, () -> {
//
//			indexer.mapIndexes(filename);
//		});
//
//		assertTrue(ioException.getMessage().contains(" was not found in the given path"));
//	}
//
//	@Test
//	void shouldRaiseErrorWhenFileIsRestricted() {
//
//		IOException ioException = assertThrows(IOException.class, () -> {
//
//			indexer.mapIndexes(sourceFilenameRestricted);
//		});
//
//		assertTrue(ioException.getMessage().startsWith("Permission denied to read"));
//	}

}

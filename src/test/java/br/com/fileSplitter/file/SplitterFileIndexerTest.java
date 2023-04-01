package br.com.fileSplitter.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import br.com.fileSplitter.file.model.MarkerEnum;

class SplitterFileIndexerTest {

	private String sourceFilename = "src/test/resources/XS-test-file-1000.txt";
	private String sourceFilenameRestricted = "src/test/resources/test-file-restrict.txt";
	private String sourceFilenameNoHeaderTrailler = "src/test/resources/XS-test-file-no-header-trailler.txt";
	private String sourceFilenameNoHeader = "src/test/resources/XS-test-file-1000-no-header.txt";
	private String sourceFilenameNoTrailler = "src/test/resources/XS-test-file-1000-no-trailler.txt";
	private File sourceFile = new File(sourceFilename);
	

	@Test
	void shouldFindAllIndexesInGivenFileName() throws SplitterFileException{

		SplitterFileIndexer indexer = new SplitterFileIndexer(sourceFilename);

		Map<String, MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
		
		var ret = indexer.mapIndexes(markers);
		assertEquals(4, ret.size());

		var firstIndex = ret.get(0);
		var lastIndex = ret.get(ret.size()-1);
		assertEquals(1,firstIndex.getFileNumber().intValue());
		assertEquals(1,firstIndex.getHeader().getLinePosition().intValue());
		assertEquals(0,firstIndex.getHeader().getPointerPosition().intValue());
		
		assertEquals(10,firstIndex.getTrailler().getLinePosition().intValue());
		assertEquals(9009,firstIndex.getTrailler().getPointerPosition().intValue());
		
		assertEquals(4,lastIndex.getFileNumber().intValue());
		assertEquals(23,lastIndex.getHeader().getLinePosition().intValue());
		assertEquals(22022,lastIndex.getHeader().getPointerPosition().intValue());
		assertEquals(31,lastIndex.getTrailler().getLinePosition().intValue());
		assertEquals(30030,lastIndex.getTrailler().getPointerPosition().intValue());
	}
	
	@Test
	void shouldFindAllIndexesInGivenSourceFile() throws SplitterFileException{

		SplitterFileIndexer indexer = new SplitterFileIndexer(sourceFile);

		Map<String, MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
		
		var ret = indexer.mapIndexes(markers);
		assertEquals(4, ret.size());

		var firstIndex = ret.get(0);
		var lastIndex = ret.get(ret.size()-1);
		assertEquals(1,firstIndex.getFileNumber().intValue());
		assertEquals(1,firstIndex.getHeader().getLinePosition().intValue());
		assertEquals(0,firstIndex.getHeader().getPointerPosition().intValue());
		
		assertEquals(10,firstIndex.getTrailler().getLinePosition().intValue());
		assertEquals(9009,firstIndex.getTrailler().getPointerPosition().intValue());
		
		assertEquals(4,lastIndex.getFileNumber().intValue());
		assertEquals(23,lastIndex.getHeader().getLinePosition().intValue());
		assertEquals(22022,lastIndex.getHeader().getPointerPosition().intValue());
		assertEquals(31,lastIndex.getTrailler().getLinePosition().intValue());
		assertEquals(30030,lastIndex.getTrailler().getPointerPosition().intValue());

	}
	
	@Test
	void shouldRaiseSplitterFileExceptionWhenMarkersNotProvided() throws SplitterFileException{

		SplitterFileIndexer indexer = new SplitterFileIndexer(sourceFile);
		Map<String, MarkerEnum> markers = null;
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			indexer.mapIndexes(markers);		
		});

		assertNotNull(exception);
		assertEquals("The markers should be provided", exception.getMessage());
	}

	@Test
	void shouldRaiseSplitterFileExceptionWhenFileDoesNotExists() throws SplitterFileException  {

		File file = new File("anything");
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			SplitterFileIndexer indexer = new SplitterFileIndexer(file);	
		});

		assertNotNull(exception);
		
	}
	
	@Test
	void shouldFindNoIndexWhenFileDoNotHaveHeader() throws SplitterFileException {

		SplitterFileIndexer indexer = new SplitterFileIndexer(sourceFilenameNoHeader);

		Map<String, MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
		
		var ret = indexer.mapIndexes(markers);
		
		assertEquals(0, ret.size());

	}
	
	@Test
	void shouldFindNoIndexWhenFileDoNotHaveTrailler() throws SplitterFileException {

		SplitterFileIndexer indexer = new SplitterFileIndexer(sourceFilenameNoTrailler);

		Map<String, MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
		
		var ret = indexer.mapIndexes(markers);
		
		assertEquals(0, ret.size());

	}

	@Test
	void shouldRaiseErrorWhenFileNameIsNotGiven() {

		File file = new File("");
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			SplitterFileIndexer indexer = new SplitterFileIndexer(file);	
		});

		assertTrue(exception.getMessage().contains(" was not found in the given path"));

	}

	@Test
	void shouldRaiseErrorWhenFileNameIsNull() {

		String fileName = null;
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			new SplitterFileIndexer(fileName);	
		});

		assertEquals("A File or filename must be provided", exception.getMessage());

	}



	@Test
	void shouldRaiseErrorWhenFileIsRestricted() throws SplitterFileException {

		Map<String, MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
		var indexer = new SplitterFileIndexer(sourceFilenameRestricted);
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			indexer.mapIndexes(markers);
		});

		assertTrue(exception.getMessage().contains("Permission denied"));
	}

}

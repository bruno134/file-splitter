package br.com.filesplitter.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import br.com.filesplitter.file.model.Index;
import br.com.filesplitter.file.model.MarkerEnum;
import br.com.filesplitter.file.model.Pointer;
import br.com.filesplitter.file.util.FileUtils;

class SplitterFileIndexerTest {

	private String sourceFilename = "src/test/resources/XS-test-file-1000.txt";
	private String sourceFilenameNoHeaderTrailler = "src/test/resources/XS-test-file-no-header-trailler.txt";
	private String sourceFilenameNoHeader = "src/test/resources/XS-test-file-1000-no-header.txt";
	private String sourceFilenameNoTrailler = "src/test/resources/XS-test-file-1000-no-trailler.txt";
	private File sourceFile = new File(sourceFilename);

	@Test
	void shouldFindAllIndexesInGivenFileName() throws SplitterFileException {

		SplitterFileIndexer indexer = new SplitterFileIndexer(sourceFilename);

		Map<String, MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);

		var ret = indexer.mapIndexes(markers);
		assertEquals(4, ret.size());

		var firstIndex = ret.get(0);
		var lastIndex = ret.get(ret.size() - 1);
		assertEquals(1, firstIndex.getFileNumber().intValue());
		assertEquals(1, firstIndex.getHeader().getLinePosition().intValue());
		assertEquals(0, firstIndex.getHeader().getPointerPosition().intValue());

		assertEquals(10, firstIndex.getTrailler().getLinePosition().intValue());
		assertEquals(9009, firstIndex.getTrailler().getPointerPosition().intValue());

		assertEquals(4, lastIndex.getFileNumber().intValue());
		assertEquals(23, lastIndex.getHeader().getLinePosition().intValue());
		assertEquals(22022, lastIndex.getHeader().getPointerPosition().intValue());
		assertEquals(31, lastIndex.getTrailler().getLinePosition().intValue());
		assertEquals(30030, lastIndex.getTrailler().getPointerPosition().intValue());
	}

	@Test
	void shouldFindAllIndexesInGivenSourceFile() throws SplitterFileException {

		SplitterFileIndexer indexer = new SplitterFileIndexer(sourceFile);

		Map<String, MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);

		var ret = indexer.mapIndexes(markers);
		assertEquals(4, ret.size());

		var firstIndex = ret.get(0);
		var lastIndex = ret.get(ret.size() - 1);
		assertEquals(1, firstIndex.getFileNumber().intValue());
		assertEquals(1, firstIndex.getHeader().getLinePosition().intValue());
		assertEquals(0, firstIndex.getHeader().getPointerPosition().intValue());

		assertEquals(10, firstIndex.getTrailler().getLinePosition().intValue());
		assertEquals(9009, firstIndex.getTrailler().getPointerPosition().intValue());

		assertEquals(4, lastIndex.getFileNumber().intValue());
		assertEquals(23, lastIndex.getHeader().getLinePosition().intValue());
		assertEquals(22022, lastIndex.getHeader().getPointerPosition().intValue());
		assertEquals(31, lastIndex.getTrailler().getLinePosition().intValue());
		assertEquals(30030, lastIndex.getTrailler().getPointerPosition().intValue());

	}

	@Test
	void shouldRaiseSplitterFileExceptionWhenMarkersNotProvided() throws SplitterFileException {

		SplitterFileIndexer indexer = new SplitterFileIndexer(sourceFile);
		Map<String, MarkerEnum> markers = null;

		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			indexer.mapIndexes(markers);
		});

		assertNotNull(exception);
		assertEquals(SplitterFileIndexer.THE_MARKERS_SHOULD_BE_PROVIDED, exception.getMessage());
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
	void shouldFindNoIndexWhenFileDoNotHaveHeaderAndTrailler() throws SplitterFileException {

		SplitterFileIndexer indexer = new SplitterFileIndexer(sourceFilenameNoHeaderTrailler);

		Map<String, MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);

		var ret = indexer.mapIndexes(markers);

		assertEquals(0, ret.size());

	}

	@Test
	void shouldRaiseCustomExceptionWhenTryIndexARestrictedFile() throws SplitterFileException {

		Map<String, MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);

		File nonreadableFile = FileUtils.createFile("src/test/resources/anyFile.txt");

		SplitterFileIndexer indexer = new SplitterFileIndexer(nonreadableFile);
		nonreadableFile.setReadable(false);

		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			indexer.mapIndexes(markers);
		});

		assertTrue(exception.getMessage().contains("Permission denied"));
		nonreadableFile.delete();

	}

	@Test
	void shouldBringAllIndexValues() {
		Pointer pointer = new Pointer(0, 1);

		Index index = new Index(1, pointer, pointer);

		assertNotNull(index.toString());
		assertNotEquals("", index.toString());

	}

	@Test
	void shouldBringAllPointersValues() {
		Pointer pointer = new Pointer(0, 1);
		assertNotNull(pointer.toString());
		assertNotEquals("", pointer.toString());
	}

}

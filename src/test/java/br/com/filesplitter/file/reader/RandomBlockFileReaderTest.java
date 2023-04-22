package br.com.filesplitter.file.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import br.com.filesplitter.file.SplitterFileException;
import br.com.filesplitter.file.model.Index;
import br.com.filesplitter.file.model.Pointer;

class RandomBlockFileReaderTest {

	private RandomBlockFileReader reader;
	private Index index;
	private final String originalFilename = "src/test/resources/XS-test-file-1000.txt";
	private final String originalFilenameRestricted = "src/test/resources/test-file-restrict.txt";

	

	@Test
	void shouldReturnTextBlockWhenIndexAndFileAreProvided() {
		try {

			index = new Index(1,new Pointer(1,0), new Pointer(10,9009));
			File file = new File(originalFilename);
			reader = new RandomBlockFileReader(file);
			
			StringBuilder sb = reader.read(index);
			
			assertEquals(10010,sb.length());
			assertTrue(sb.toString().startsWith("@@H"));
			assertEquals('9',sb.toString().charAt(10008));
			
		} catch (SplitterFileException e) {
			e.printStackTrace();
		}
	}

	@Test
	void shouldRaiseErrorWhenFileIsNull() {

		index = new Index(1,new Pointer(1,1), new Pointer(2,2));
		File nullFile = null;
		reader = new RandomBlockFileReader(nullFile);
		
		SplitterFileException ioException = assertThrows(SplitterFileException.class, () -> {
			reader.read(index);
		});

		assertEquals("Source file cannot be null",ioException.getMessage());

	}
	
	@Test
	void shouldRaiseErrorWhenFileDoesNotExists() {

		index = new Index(1,new Pointer(1,1), new Pointer(2,2));
		File file = new File("dummyFile.file");
		reader = new RandomBlockFileReader(file);
		
		SplitterFileException ioException = assertThrows(SplitterFileException.class, () -> {
			reader.read(index);
		});

		assertTrue(ioException.getMessage().contains(file.getName()));
		assertTrue(ioException.getMessage().contains("was not found in the given path"));

	}
	
	@Test
	void shouldRaiseErrorWhenFileIsRestricted() {
		
		index = new Index(1,new Pointer(1,1), new Pointer(2,2));
		File file = new File(originalFilenameRestricted);
		reader = new RandomBlockFileReader(file);
		
		SplitterFileException ioException = assertThrows(SplitterFileException.class, () -> {
			reader.read(index);
		});

		assertTrue(ioException.getMessage().startsWith("Permission denied to read"));
	}

	@Test
	void shouldRaiseErrorWhenIndexIsNull() {

		File file = new File(originalFilename);
		reader = new RandomBlockFileReader(file);
		
		SplitterFileException readerException = assertThrows(SplitterFileException.class, () -> {
			reader.read(index);
		});

		assertEquals("Index was not provided", readerException.getMessage());
		
		
	}
}

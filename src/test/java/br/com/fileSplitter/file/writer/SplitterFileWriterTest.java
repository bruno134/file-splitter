package br.com.fileSplitter.file.writer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import br.com.fileSplitter.file.SplitterFileException;

class SplitterFileWriterTest {

	private final String fileName = "src/test/resources/out/MyTestFile.txt";
	private final File file = new File(fileName);
	private StringBuilder sb;
	private SplitterFileWriter splitterFileWriter;
	
	
	

	@Test
	void shouldCreateFileWhenStringBuilderIsGiven() throws SplitterFileException {

		splitterFileWriter = new SplitterFileWriter(file);
		sb = new StringBuilder();
		splitterFileWriter.write(sb);
		assertTrue(file.exists());

	}

	
	@Test
	void shouldRaiseErrorWhenFileIsNull() {	
		
		splitterFileWriter = new SplitterFileWriter(null);
		sb = new StringBuilder();
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			splitterFileWriter.write(sb);
		});
		
		assertEquals("Target File not provided",exception.getMessage());

	}
	
	@Test
	void shouldRaiseErrorWhenStringBuilderIsNull() {	
		
		splitterFileWriter = new SplitterFileWriter(file);
		sb = null;
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			splitterFileWriter.write(sb);
		});
		
		assertEquals("StringBuilder cannot be null",exception.getMessage());

	}
	
	@Test
	void shouldRaiseIOExceptionWhenPathDoesNotExists() {	
		
		File newFile = new File("/anything/any.txt");
		splitterFileWriter = new SplitterFileWriter(newFile);
		sb = new StringBuilder();
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			splitterFileWriter.write(sb);
		});
		
		assertNotNull(exception);

	}
	
}

package br.com.fileSplitter.file.writer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

class SplitterFileWriterTest {

	@Test
	void shouldCreateFileWhenNameIsProvided() {

		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		String fileName = "MyTestFile.txt";

		File file = splitterFileWriter.createFile(fileName);

		assertNotNull(file);
		assertEquals(file.getClass(), File.class);

	}
	
	@Test
	void shouldRaiseCustomErrorWhenFileNameIsNotProvided() {
		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		StringBuilder sb = null;

		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
			splitterFileWriter.writeAndSaveFile(sb, 0, 1);
		});

		assertTrue(exception.getMessage().equals("A customFileName was not provided"));
	}

	@Test
	void shouldCreateFileWhenFolderDoesntExists() {

		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		String fileName = "MyTestFile.txt";

		File file = splitterFileWriter.createFile(fileName);

		assertNotNull(file);
		assertEquals(file.getClass(), File.class);
	}

	@Test
	void shouldRaiseCustomErrorWhenStringBuilderIsNull() {
		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		StringBuilder sb = null;

		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
			splitterFileWriter.writeAndSaveFile(sb, 0, 1);
		});

		assertTrue(exception.getMessage().equals("StringBuilder cannot be null"));

	}

	@Test
	void shouldRaiseCustomErrorWhenStringBuilderIsEmpty() {
		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		StringBuilder sb = null;

		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
			splitterFileWriter.writeAndSaveFile(sb, 0, 1);
		});

		assertTrue(exception.getMessage().equals("StringBuilder cannot be empty"));
	}

	@Test
	void shouldRaiseCustomErrorWhenFirstLineIsNull() {
		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		StringBuilder sb = null;

		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
			splitterFileWriter.writeAndSaveFile(sb, 0, 1);
		});

		assertTrue(exception.getMessage().equals("First line of file must be provided"));
	}

	@Test
	void shouldRaiseCustomErrorWhenLastLineIsNull() {
		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		StringBuilder sb = null;

		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
			splitterFileWriter.writeAndSaveFile(sb, 0, 1);
		});

		assertTrue(exception.getMessage().equals("Last line of the file must be provided"));
	}


}

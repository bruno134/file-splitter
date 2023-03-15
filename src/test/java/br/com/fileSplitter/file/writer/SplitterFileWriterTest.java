package br.com.fileSplitter.file.writer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class SplitterFileWriterTest {
	
	private static String TEST_FILE = "src/test/resources/out/MyTestFile.txt";

	@Test
	void shouldCreateFileWhenNameIsProvided() {

		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		String fileName = TEST_FILE;

		File file;

		try {
			file = splitterFileWriter.createFile(fileName);
						
			assertNotNull(file);
			assertEquals(file.getPath(), fileName);
		} catch (FileWriterException e) {
			e.printStackTrace();
		}

	}

	@Test
	void shouldRaiseCustomErrorWhenFileNameIsNotProvided() {
		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();


		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
			File file = splitterFileWriter.createFile(null);
		});

		assertTrue(exception.getMessage().equals("A File must be provided"));
	}
	
	@Test
	void shouldRaiseCustomErrorWhenPathDoesNotExist() {
		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();


		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
			File file = splitterFileWriter.createFile("/nemTemessaPasta/teste.txt");
		});

		assertTrue(exception.getMessage().equals("File path not found or invalid"));
	}

	@Test
	void shouldCreateFileWhenFolderDoesNotExists() {

		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		String fileName = TEST_FILE;

		File file;
		try {
			file = splitterFileWriter.createFile(fileName);
			assertNotNull(file);
			assertEquals(file.getPath(), fileName);
		} catch (FileWriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	void shouldRaiseCustomErrorFileNotProvided() {
		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		StringBuilder sb = new StringBuilder();
		File file = null;

		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
			splitterFileWriter.writeAndSaveFile(file, sb, 0, 1);
		});

		assertTrue(exception.getMessage().equals("File cannot be null"));
	}

	@Test
	void shouldRaiseCustomErrorWhenFirstLineIsNull() {
		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		StringBuilder sb = new StringBuilder();

		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
			File file = null;

			try {
				file = splitterFileWriter.createFile(TEST_FILE);
			} catch (FileWriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			splitterFileWriter.writeAndSaveFile(file, sb, null, 1);
		});

		assertTrue(exception.getMessage().equals("First/Last line of file must be provided"));
	}

	@Test
	void shouldRaiseCustomErrorWhenLastLineIsNull() {
		SplitterFileWriter splitterFileWriter = new SplitterFileWriter();

		StringBuilder sb = new StringBuilder();

		FileWriterException exception = assertThrows(FileWriterException.class, () -> {

			File file = null;

			try {
				file = splitterFileWriter.createFile(TEST_FILE);
			} catch (FileWriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			splitterFileWriter.writeAndSaveFile(file, sb, 0, null);
		});

		assertTrue(exception.getMessage().equals("First/Last line of file must be provided"));
	}

}

package br.com.fileSplitter.file.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class RandomBlockFileReaderTest {

	private RandomBlockFileReader reader;
	private String originalFilename = "src/test/resources/XS-test-file-1000.txt";
	private String originalFilenameRestricted = "src/test/resources/test-file-restrict.txt";
	private File originalFile = new File(originalFilename);
	private Integer firstPointer = 11;
	private Integer lastPointer = 17;
	private Integer lengthLine = 1000;

//	@Test
//	void shouldReturnTextBlockWhenLinesPointerAndFileAreProvided() {
//		try {
//
//			var stringBuffer = reader.readBlock(originalFile, lengthLine, firstPointer, lastPointer);
//			var fileLines = stringBuffer.toString().split("\n");
//			var qtyLines = fileLines.length;
//
//			assertFalse((stringBuffer.isEmpty()));
//			assertEquals(7, qtyLines);
//			assertTrue(fileLines[0].startsWith("@"));
//			assertTrue(fileLines[qtyLines - 1].startsWith("9"));
//		} catch (IOException | RandomBlockFileReaderException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	void shouldRaiseErrorWhenFileNameIsNotGiven() {
//
//		IOException ioException = assertThrows(IOException.class, () -> {
//			reader.readBlock("", lengthLine, firstPointer, lastPointer);
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
//		RandomBlockFileReaderException readerException = assertThrows(RandomBlockFileReaderException.class, () -> {
//			reader.readBlock(filename, lengthLine, firstPointer, lastPointer);
//		});
//
//		assertTrue(readerException.getMessage().equals("Original file can't be null"));
//
//	}
//
//	@Test
//	void shouldRaiseErrorWhenFileIsNull() {
//
//		File file = null;
//
//		RandomBlockFileReaderException readerException = assertThrows(RandomBlockFileReaderException.class, () -> {
//			reader.readBlock(file, lengthLine, firstPointer, lastPointer);
//		});
//
//		assertTrue(readerException.getMessage().equals("Original file can't be null"));
//
//	}
//
//	@Test
//	void shouldRaiseErrorWhenGivenFileIsNotFound() {
//		String filename = "UnfindbleFile.txt";
//
//		IOException ioException = assertThrows(IOException.class, () -> {
//			reader.readBlock(filename, lengthLine, firstPointer, lastPointer);
//		});
//
//		assertTrue(ioException.getMessage().contains(" was not found in the given path"));
//	}
//
//	@Test
//	void shouldRaiseErrorWhenFileIsRestricted() {
//		
//		IOException ioException = assertThrows(IOException.class, () -> {
//			reader.readBlock(originalFilenameRestricted, lengthLine, firstPointer, lastPointer);
//		});
//
//		assertTrue(ioException.getMessage().startsWith("Permission denied to read"));
//	}
//
//	@Test
//	void shouldRaiseErrorWhenFirstPointerNameIsNotGiven() {
//
//		RandomBlockFileReaderException readerException = assertThrows(RandomBlockFileReaderException.class, () -> {
//			reader.readBlock(originalFilename, lengthLine, null, lastPointer);
//		});
//
//		assertTrue(readerException.getMessage().equals("firstPointer|lastPointer|lineLength can't be null"));
//		
//		
//	}
//
//	@Test
//	void shouldRaiseErrorWhenLastPointerNameIsNotGiven() {
//		RandomBlockFileReaderException readerException = assertThrows(RandomBlockFileReaderException.class, () -> {
//			reader.readBlock(originalFilename, lengthLine, firstPointer, null);
//		});
//
//		assertTrue(readerException.getMessage().equals("firstPointer|lastPointer|lineLength can't be null"));
//	}
//	
//	
//	@Test
//	void shouldRaiseErrorWhenLengthIsNotGiven() {
//		RandomBlockFileReaderException readerException = assertThrows(RandomBlockFileReaderException.class, () -> {
//			reader.readBlock(originalFilename, null, firstPointer, lastPointer);
//		});
//
//		assertTrue(readerException.getMessage().equals("firstPointer|lastPointer|lineLength can't be null"));
//	}

}

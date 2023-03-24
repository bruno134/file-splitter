package br.com.fileSplitter.file.writer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

class SplitterFileWriterTest {

	private final String fileName = "src/test/resources/out/MyTestFile.txt";
	private final String destinationPath  = "src/test/resources/out/";
	private final File file = new File(fileName);
	private final StringBuilder sb = new StringBuilder();
	private final Integer firstPointer = 0;
	private final Integer finalPointer = 1;
	//private final SplitterFileWriter splitterFileWriter;
	
	
	

//	@Test
//	void shouldCreateFileWhenFileNameIsGiven() {
//
//		try {
//			String fileGenerated = splitterFileWriter.writeAndSaveFile(fileName,destinationPath,sb,firstPointer,finalPointer);
//
//			fileGenerated = fileGenerated.replace(".txt","");
//			assertNotNull(fileGenerated);
//			assertFalse(fileGenerated.isEmpty());
//			assertTrue(fileGenerated.startsWith(fileName.split("/")[4].replace(".txt", "")));
//		} catch (FileWriterException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	
//	@Test
//	void shouldCreateFileWhenFileNameIsNotGiven() {	
//		
//		try {
//			String fileGenerated = splitterFileWriter.writeAndSaveFile(sb,firstPointer,finalPointer);
//
//			assertNotNull(fileGenerated);
//			assertFalse(fileGenerated.isEmpty());			
//		} catch (FileWriterException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	@Test
//	void shouldCreateFileWhenFileIsGiven() {	
//		
//		try {
//			String fileGenerated = splitterFileWriter.writeAndSaveFile(file,sb,firstPointer,finalPointer);
//
//			assertNotNull(fileGenerated);
//			assertFalse(fileGenerated.isEmpty());			
//		} catch (FileWriterException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//
//	@Test
//	void shouldRaiseCustomErrorWhenFileNameIsEmpty() {
//		
//		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
//			splitterFileWriter.writeAndSaveFile("",destinationPath,sb,firstPointer,finalPointer);
//		});
//		
//		assertTrue(exception.getMessage().equals("A File or filename must be provided"));
//
//	}
//	
//
//	@Test
//	void shouldRaiseCustomErrorWhenStringBuilderIsNull() {
//
//		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
//			splitterFileWriter.writeAndSaveFile(null, firstPointer, finalPointer);
//		});
//
//		assertTrue(exception.getMessage().equals("StringBuilder cannot be null"));
//
//	}
//
//	@Test
//	void shouldRaiseCustomErrorFileNotProvided() {
//		File nullFile = null;
//		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
//			splitterFileWriter.writeAndSaveFile(nullFile, sb, firstPointer, finalPointer);
//		});
//
//		assertTrue(exception.getMessage().equals("File cannot be null"));
//	}
//
//	@Test
//	void shouldRaiseCustomErrorWhenFirstLineIsNull() {
//		
//
//		FileWriterException exception = assertThrows(FileWriterException.class, () -> {
//			splitterFileWriter.writeAndSaveFile(file, sb, null, finalPointer);
//		});
//
//		assertTrue(exception.getMessage().equals("First/Last line of file must be provided"));
//	}
//
//	@Test
//	void shouldRaiseCustomErrorWhenLastLineIsNull() {
//		
//
//		FileWriterException exception = assertThrows(FileWriterException.class, () -> {		
//			splitterFileWriter.writeAndSaveFile(file, sb, firstPointer, null);
//		});
//
//		assertTrue(exception.getMessage().equals("First/Last line of file must be provided"));
//	}

}

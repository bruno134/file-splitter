package br.com.fileSplitter.file.utils;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import br.com.filesplitter.file.SplitterFileException;
import br.com.filesplitter.file.util.FileUtils;

class FileUtilsTest {

	
	@Test
	void shouldRaisCustomExceptionWhenValidateFileIsNull() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.fileValidate(null);	
		});
		
		assertEquals("Source file can't be null", exception.getMessage());
		
	}
	
	@Test
	void shouldRaisCustomExceptionWhenValidateFileDoesNotExists() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.fileValidate(new File("anyfile.txt"));	
		});
		
		assertTrue(exception.getMessage().contains("was not found in the given path"));
		
	}
	
	@Test
	void shouldRaisCustomExceptionWhenDoesHavePermissionTopReadFile() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.fileValidate(new File("src/test/resources/test-file-restrict.txt"));	
		});
		
		assertTrue(exception.getMessage().contains("Permission denied to read"));
		
	}
	
	@Test
	void shouldNotRaisCustomExceptionWhenFileIsValid() {
		
		boolean validated = true;
		try {
			FileUtils.fileValidate(new File("src/test/resources/XS-test-file-1000.txt"));
		} catch (SplitterFileException e) {
			validated = false;
		}	
		
		assertTrue(validated);
	}
	
	@Test
	void shouldNotCreateFileWhenFileNameIsNull() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.createFile(null);	
		});
		
		assertEquals("A File or filename must be provided", exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenCouldNotCreateDirectory() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.createFile("src/test/resources/ffolder/nfolder/file.txt");	
		});
		
		assertEquals("Could not create path or path invalid", exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenCouldNotCreateFile() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.createFile("src/test/resources/ffolder/file.txt");	
		});
		
		assertEquals("Could not create path or path invalid", exception.getMessage());
		
	}
	
	@Test
	void shouldCreateFileWhenFolderExists() throws SplitterFileException {
		
		File createdFile = FileUtils.createFile("src/test/resources/file.txt");	
		
		assertTrue(createdFile.exists());
		createdFile.delete();
	}
	
	@Test
	void shouldCreateFileWhenFolderDoesNotExists() throws SplitterFileException {
		
		File createdFile = FileUtils.createFile("src/test/resources/newFolder/file.txt");	
		
		assertTrue(createdFile.exists());
		createdFile.delete();
		new File(createdFile.getParent()).delete();
		
	}
	
	@Test
	void shouldReturnFilePath() throws SplitterFileException {
		
		String path = FileUtils.returnFilePath("src/test/resources/XS-test-file-1000.txt");

		assertNotEquals("", path);
		assertTrue(path.contains("src/test/resources"));
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileNameIsNull() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.returnFilePath(null);	
		});
		
		assertEquals("A File or filename must be provided", exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileNameIsBlank() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.returnFilePath("");	
		});
		
		assertEquals("A File or filename must be provided", exception.getMessage());
		
	}

}

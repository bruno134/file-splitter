package br.com.filesplitter.file.utils;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import br.com.filesplitter.file.SplitterFileException;
import br.com.filesplitter.file.util.FileUtils;

class FileUtilsTest {

	
	@Test
	void shouldRaisCustomExceptionWhenValidateFileIsNull() {
		
		File file = null;
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.fileValidate(file);	
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
	void shouldRaiseCustomExceptionWhenItsAlreadyExists() throws SplitterFileException, IOException {
		
		
		File alreadyExistsFile = FileUtils.createFile("src/test/resources/newFolder/file.txt");	
				
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			 FileUtils.createFile("src/test/resources/newFolder/file.txt");	
		});
				
		assertEquals("Could not create file", exception.getMessage());
		
		alreadyExistsFile.delete();
		new File(alreadyExistsFile.getParent()).delete();
		
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
	
	@Test
	void shouldRaiseCustomExceptionWhenFileNameIsBlankInCreateFile() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.createFile("");	
		});
		
		assertEquals("A File or filename must be provided", exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileNameIsBlankInValidationFile() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.fileValidate("");	
		});
		
		assertEquals("Source file can't be blank", exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileNameIsNullInValidationFile() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			String file = null;
			FileUtils.fileValidate(file);	
		});
		
		assertEquals("Source file can't be null", exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileCantBeReadable() {
		
		File file = new File("src/test/resources/dummy_file.txt");
		
		try {			
			file.createNewFile();
			file.setReadable(false);
		} catch (IOException e) {		
			fail("Couldn't create file to test");
		}
		
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
		
			
			FileUtils.fileValidate(file);	
		});
		
		assertTrue(exception.getMessage().contains("Can't read the file"));
		file.delete();
		
	}
	
	@Test
	void shouldValidateFileSuccessfully() {

		SplitterFileException exception = null;

		try {
			String file = "src/test/resources/XS-test-file-1000.txt";
			FileUtils.fileValidate(file);
		} catch (SplitterFileException e) {
			exception = e;
		}

		assertEquals(null, exception);

	}

}

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

	
	private static final String COULDN_T_CREATE_FILE_TO_TEST = "Couldn't create file to test";
	private static final String CAN_T_READ_THE_FILE = "Can't read the file";
	private static final String DUMMY_FILE_TXT = "src/test/resources/dummy_file.txt";
	private static final String FOLDER_RESOURCES = "src/test/resources";
	private static final String COULD_NOT_CREATE_FILE = "Could not create file";
	private static final String FILE_TXT = "src/test/resources/file.txt";
	private static final String NEW_FOLDER_FILE_TXT = "src/test/resources/newFolder/file.txt";
	private static final String XS_TEST_FILE_1000_TXT = "src/test/resources/XS-test-file-1000.txt";
	private static final String ANYFILE_TXT = "anyfile.txt";

	@Test
	void shouldRaisCustomExceptionWhenValidateFileIsNull() {
		
		File file = null;
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.fileValidate(file);	
		});
		
		assertEquals(FileUtils.SOURCE_FILE_CAN_T_BE_NULL, exception.getMessage());
		
	}
	
	@Test
	void shouldRaisCustomExceptionWhenValidateFileDoesNotExists() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.fileValidate(new File(ANYFILE_TXT));	
		});
		
		assertTrue(exception.getMessage().contains(FileUtils.WAS_NOT_FOUND_IN_THE_GIVEN_PATH));
		
	}
	
	@Test
	void shouldNotRaisCustomExceptionWhenFileIsValid() {
		
		boolean validated = true;
		try {
			FileUtils.fileValidate(new File(XS_TEST_FILE_1000_TXT));
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
		
		assertEquals(FileUtils.A_FILE_OR_FILENAME_MUST_BE_PROVIDED, exception.getMessage());
		
	}
			
	@Test
	void shouldCreateFileWhenFolderExists() throws SplitterFileException {
		
		File createdFile = FileUtils.createFile(FILE_TXT);	
		
		assertTrue(createdFile.exists());
		createdFile.delete();
	}
	
	@Test
	void shouldCreateFileWhenFolderDoesNotExists() throws SplitterFileException {
		
		File createdFile = FileUtils.createFile(NEW_FOLDER_FILE_TXT);	
		
		assertTrue(createdFile.exists());
		createdFile.delete();
		new File(createdFile.getParent()).delete();
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenItsAlreadyExists() throws SplitterFileException, IOException {
		
		
		File alreadyExistsFile = FileUtils.createFile(NEW_FOLDER_FILE_TXT);	
				
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			 FileUtils.createFile(NEW_FOLDER_FILE_TXT);	
		});
				
		assertEquals(COULD_NOT_CREATE_FILE, exception.getMessage());
		
		alreadyExistsFile.delete();
		new File(alreadyExistsFile.getParent()).delete();
		
	}
	
	@Test
	void shouldReturnFilePath() throws SplitterFileException {
		
		String path = FileUtils.returnFilePath(XS_TEST_FILE_1000_TXT);

		assertNotEquals("", path);
		assertTrue(path.contains(FOLDER_RESOURCES));
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileNameIsNull() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.returnFilePath(null);	
		});
		
		assertEquals(FileUtils.A_FILE_OR_FILENAME_MUST_BE_PROVIDED, exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileNameIsBlank() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.returnFilePath("");	
		});
		
		assertEquals(FileUtils.A_FILE_OR_FILENAME_MUST_BE_PROVIDED, exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileNameIsBlankInCreateFile() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.createFile("");	
		});
		
		assertEquals(FileUtils.A_FILE_OR_FILENAME_MUST_BE_PROVIDED, exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileNameIsBlankInValidationFile() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			FileUtils.fileValidate("");	
		});
		
		assertEquals(FileUtils.SOURCE_FILE_CAN_T_BE_BLANK, exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileNameIsNullInValidationFile() {
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			String file = null;
			FileUtils.fileValidate(file);	
		});
		
		assertEquals(FileUtils.SOURCE_FILE_CAN_T_BE_NULL, exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenFileCantBeReadable() {
		
		File file = new File(DUMMY_FILE_TXT);
		
		try {			
			file.createNewFile();
			file.setReadable(false);
		} catch (IOException e) {		
			fail(COULDN_T_CREATE_FILE_TO_TEST);
		}
		
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
		
			
			FileUtils.fileValidate(file);	
		});
		
		assertTrue(exception.getMessage().contains(CAN_T_READ_THE_FILE));
		file.delete();
		
	}
	
	@Test
	void shouldValidateFileSuccessfully() {

		SplitterFileException exception = null;

		try {
			String file = XS_TEST_FILE_1000_TXT;
			FileUtils.fileValidate(file);
		} catch (SplitterFileException e) {
			exception = e;
		}

		assertEquals(null, exception);

	}

}

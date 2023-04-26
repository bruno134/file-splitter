package br.com.filesplitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import br.com.filesplitter.file.SplitterFileException;
import br.com.filesplitter.file.model.MarkerEnum;
import br.com.filesplitter.file.model.SplitterFileConfiguration;

class SplitterTest {

	private static final String TXT_EXTENSION = ".txt";
	private static final String SPLITTED_FILE = "splitted_file";
	private static final String TEST_FILE = "test_file";
	private static final String SOURCE_FILE = "src/test/resources/XS-test-file-1000.txt";
	private static final String TARGET_PATH = "src/test/resources/unit-tests";
	
	@Test
	void shouldSplitBigFileIntoSmallers() throws SplitterFileException {
		
		Map<String,MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
		
		String fileName = TEST_FILE;
		
		SplitterFileConfiguration config = SplitterFileConfiguration.builder(markers)
				.withOutputFileName(fileName)
				.build();
		
		Splitter splitter = new Splitter(config);
		splitter.splitAndWrite(SOURCE_FILE, TARGET_PATH);
		
		File testPath = new File(TARGET_PATH);
		String[] files = testPath.list();
		
		assertTrue(testPath.exists());
		assertEquals(4, files.length);
		assertTrue(files[0].endsWith(TXT_EXTENSION));
		assertTrue(files[0].startsWith(fileName));

		deleteFile(testPath);
	}
	
	@Test
	void shouldSplitBigFileIntoSmallersWithProvidedExtension() throws SplitterFileException {


		Map<String,MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
		
		String fileName = TEST_FILE;
		String extension = ".dat";
		
		SplitterFileConfiguration config = SplitterFileConfiguration.builder(markers)
				.withOutputFileName(fileName)
				.withOutputFileExtension(extension)				
				.build();
		
		Splitter splitter = new Splitter(config);
		splitter.splitAndWrite(SOURCE_FILE, TARGET_PATH);
		
		File testPath = new File(TARGET_PATH);
		String[] files = testPath.list();
		
		assertTrue(testPath.exists());
		assertEquals(4, files.length);
		assertTrue(files[0].endsWith(extension));
		assertTrue(files[0].startsWith(fileName));
		
		deleteFile(testPath);
		
	}
	
	@Test
	void shouldSplitBigFileIntoSmallersWhenFileNameIsNotProvided() throws SplitterFileException {


		Map<String,MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
				
		String extension = TXT_EXTENSION;
		
		SplitterFileConfiguration config = SplitterFileConfiguration.builder(markers)				
				.withOutputFileExtension(extension)				
				.build();
		
		Splitter splitter = new Splitter(config);
		splitter.splitAndWrite(SOURCE_FILE, TARGET_PATH);
		
		File testPath = new File(TARGET_PATH);
		String[] files = testPath.list();
		
		assertTrue(testPath.exists());
		assertEquals(4, files.length);
		assertTrue(files[0].endsWith(extension));
		assertTrue(files[0].startsWith(SPLITTED_FILE));
		
		deleteFile(testPath);
		
		

	}
	
	@Test
	void shouldSplitBigFileIntoSmallersWhenFileNameAndExtensioIsNotProvided() throws SplitterFileException {


		Map<String,MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
		
		SplitterFileConfiguration config = SplitterFileConfiguration.builder(markers)							
				.build();
		
		Splitter splitter = new Splitter(config);
		splitter.splitAndWrite(SOURCE_FILE, TARGET_PATH);
		
		File testPath = new File(TARGET_PATH);
		String[] files = testPath.list();
		
		assertTrue(testPath.exists());
		assertEquals(4, files.length);
		assertTrue(files[0].endsWith(TXT_EXTENSION));
		assertTrue(files[0].startsWith(SPLITTED_FILE));
		
		deleteFile(testPath);
		
	}
	
	@Test
	void shouldSplitBigFileIntoSmallersWithMoreThanOneThread() throws SplitterFileException {
		
		Map<String,MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
		
		String fileName = TEST_FILE;
		
		SplitterFileConfiguration config = SplitterFileConfiguration.builder(markers)
				.withOutputFileName(fileName)
				.withThreadPool(2)
				.build();
		
		Splitter splitter = new Splitter(config);
		splitter.splitAndWrite(SOURCE_FILE, TARGET_PATH);
		
		File testPath = new File(TARGET_PATH);
		String[] files = testPath.list();
		
		assertTrue(testPath.exists());
		assertEquals(4, files.length);
		assertTrue(files[0].endsWith(TXT_EXTENSION));
		assertTrue(files[0].startsWith(fileName));

		deleteFile(testPath);
	}
	
	@Test
	void shouldSplitBigFileIntoSmallersWhenFilePathEndsWithSlash() throws SplitterFileException {


		Map<String,MarkerEnum> markers = new HashMap<>();
		markers.put("@", MarkerEnum.HEADER);
		markers.put("9", MarkerEnum.TRAILLER);
		
		String newPath = TARGET_PATH+"/";
		
		SplitterFileConfiguration config = SplitterFileConfiguration.builder(markers)							
				.build();
		
		Splitter splitter = new Splitter(config);
		splitter.splitAndWrite(SOURCE_FILE, newPath);
		
		File testPath = new File(TARGET_PATH);
		String[] files = testPath.list();
		
		assertTrue(testPath.exists());
		assertEquals(4, files.length);
		assertTrue(files[0].endsWith(TXT_EXTENSION));
		assertTrue(files[0].startsWith(SPLITTED_FILE));
		
		deleteFile(testPath);
		
	}
	
	private void deleteFile(File file) {
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		var files = file.list();
		
		for (int i = 0; i < files.length; i++) {
			
			File newFile = new File(file.getPath() + "/" + files[i]);
			
			if((newFile.exists())) {
//				System.out.println(newFile.getName());
				newFile.delete();
			}
		}		
		file.delete();		
	}

}

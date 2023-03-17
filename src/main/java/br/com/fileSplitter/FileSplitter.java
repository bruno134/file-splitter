package br.com.fileSplitter;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSplitter {

	private static final Logger LOG = LoggerFactory.getLogger(FileSplitter.class);
	
	private static String originalFilename = "src/test/resources/XS-test-file-1000.txt";
	private static String originalFilenameRestricted = "src/test/resources/test-file-restrict.txt";
	
	public static void main(String[] args) throws IOException {
		
		File file = new File(originalFilename);
		File file2 = new File(originalFilenameRestricted);
		
		
		
		
		System.out.println(file.canRead());
		System.out.println(file2.canRead());
		
		//file.createNewFile();
		
		
		LOG.info("Hello World");
		
		
		
		

	}
	
}

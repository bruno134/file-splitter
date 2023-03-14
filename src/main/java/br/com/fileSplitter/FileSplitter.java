package br.com.fileSplitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSplitter {

	private static final Logger LOG = LoggerFactory.getLogger(FileSplitter.class);
	
	public static void main(String[] args) {
		
		String fileName = String.format("File_%d%d_%d.txt", 3, 7, System.currentTimeMillis());
		
		LOG.info("Hello World");
		LOG.info(fileName);
		
		
		

	}
	
}

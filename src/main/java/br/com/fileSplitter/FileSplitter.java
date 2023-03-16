package br.com.fileSplitter;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSplitter {

	private static final Logger LOG = LoggerFactory.getLogger(FileSplitter.class);
	
	public static void main(String[] args) throws IOException {
		
		
		File file = new File("//Users/brunodn/novo/sugarbaby.txt");
		
		if(!file.exists()) {
			File newDirectory = new File(file.getParent());
			System.out.println("Criei - " + newDirectory.mkdirs());
			//System.out.println(file.getParentFile());
		}
		
		//file.createNewFile();
		
		
		LOG.info("Hello World");
		
		
		
		

	}
	
}

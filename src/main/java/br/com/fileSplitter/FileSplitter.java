package br.com.fileSplitter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSplitter {

	private static final Logger LOG = LoggerFactory.getLogger(FileSplitter.class);
	private static String originalFilename = "src/test/resources/XS-test-file-1000.txt";
	
	public static void main(String[] args) throws IOException {
		
		RandomAccessFile reader = new RandomAccessFile(new File(originalFilename),"r");
		
		reader.seek(0);
		
		//System.out.println(reader.read());
		
		var test = reader.readLine();
		
		
		System.out.println(test.length()+1);
		System.out.println(test.charAt(0));
		System.out.println(test.charAt(1));
		System.out.println(test.charAt(2));
		
		
		

	}
	
}

package br.com.fileSplitter.file;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.jupiter.api.Test;

class SplitterFileWorkerTest {

	//TODO Complete with more tests
	
	Queue<int[]> queue;
	SplitterFileWorker worker;
	File sourceFile;
	String destinationPath;
	
	@Test
	void whenQueueIsEmptyMustWaitAtLeastTwoSecondsBeforeTerminate() {
//		
//		queue = new ConcurrentLinkedQueue<>();
//		sourceFile = new File("Test.txt");
//		destinationPath = "src/test/resources/out/";
//		String destinationFile = "Test_file";
//		worker = new SplitterFileWorker(sourceFile, destinationFile, destinationPath, queue);
//		long start = System.currentTimeMillis();
//		
//			worker.run();
//		
//		long end = (System.currentTimeMillis()-start)/1000;
//		
//		assertTrue(end>=2);	
	}

}

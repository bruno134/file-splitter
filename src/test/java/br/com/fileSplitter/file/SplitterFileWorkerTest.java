package br.com.fileSplitter.file;

import static org.junit.Assert.assertTrue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.jupiter.api.Test;

import br.com.fileSplitter.SplitterFileWorker;

class SplitterFileWorkerTest {

	//TODO Complete with more tests
	
	Queue<int[]> queue;
	SplitterFileWorker worker;
	
	@Test
	void whenQueueIsEmptyMustWaitAtLeastTwoSecondsBeforeTerminate() {
		
		queue = new ConcurrentLinkedQueue<>();
		
		worker = new SplitterFileWorker("Test.txt",queue);
		long start = System.currentTimeMillis();
		
			worker.run();
		
		long end = (System.currentTimeMillis()-start)/1000;
		
		assertTrue(end>=2);
		
		
	}
	
	@Test
	void whenQueueIsEmptyRetryIsGivenMustWaitAtLeastLessThanTwoSecondsBeforeTerminate() {
		
		queue = new ConcurrentLinkedQueue<>();
		
		worker = new SplitterFileWorker("Test.txt",queue,1);
		long start = System.currentTimeMillis();
		
			worker.run();
		
		long end = (System.currentTimeMillis()-start)/1000;
		
		assertTrue(end<2);
		
		
	}

}

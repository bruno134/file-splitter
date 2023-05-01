package br.com.filesplitter.file;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.awaitility.Awaitility.*;

import java.time.Duration;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


import org.junit.jupiter.api.Test;

import br.com.filesplitter.file.model.Index;

class ProcessorMonitorTest {	
	
	@Test
	void shouldInterruptWhenQueueIsNull() throws InterruptedException {
		
		Queue<Index> queue = null;
		ExecutorService service = Executors.newFixedThreadPool(1);
		
		ProcessorMonitor monitor = new ProcessorMonitor(queue, service);
				
		var monitorThread = new Thread(monitor);
		monitorThread.start();	

		await()
			.atMost(Duration.ofSeconds(2))
		.with()
			.until(monitor.isTerminated());
	
		assertFalse(monitorThread.isAlive());		
		
	}
	
	
	
	@Test
	void shouldInterruptWhenExecutorsIsNull() throws InterruptedException {
		
		Queue<Index> queue = new LinkedBlockingQueue<>();
		ExecutorService service = null;
		
		ProcessorMonitor monitor = new ProcessorMonitor(queue, service);
				
		var monitorThread = new Thread(monitor);
		monitorThread.start();	
		
		await()
			.atMost(Duration.ofSeconds(2))
		.with()
			.until(monitor.isTerminated());
	
		assertFalse(monitorThread.isAlive());		
		
	}
	
	// @Test
	// void shouldBeRunningWhenStillHaveProcessRunning() throws InterruptedException {
		
	// 	Queue<Index> queue = new LinkedBlockingQueue<>();
	// 	ExecutorService service = Executors.newFixedThreadPool(1);
		
	// 	ProcessorMonitor monitor = new ProcessorMonitor(queue, service);
	// 	var monitorThread = new Thread(monitor);
	// 	monitorThread.start();	
		
	// 	await()
	// 		.atMost(Duration.ofSeconds(2))
	// 	.with()
	// 		.until(monitor.isTerminated());
			
	// 	assertFalse(service.isTerminated());		
		
	// }
	
	@Test
	void shouldBeRunningWhenQueueIsNotEmpty() throws InterruptedException {
		
		Queue<Index> queue = new LinkedBlockingQueue<>();
		queue.add(new Index(null,null,null));
		ExecutorService service = Executors.newFixedThreadPool(1);
		
		ProcessorMonitor monitor = new ProcessorMonitor(queue, service);
		var monitorThread = new Thread(monitor);
		monitorThread.start();		
				
		assertFalse(service.isTerminated());
		assertTrue(monitorThread.isAlive());
		queue.poll();
		
	}
	
	@Test
	void shouldInterruptWhenQueueIsEmpty() throws InterruptedException {
		
		Queue<Index> queue = new LinkedBlockingQueue<>();
		queue.add(new Index(null,null,null));
		ExecutorService service = Executors.newFixedThreadPool(1);		
		ProcessorMonitor monitor = new ProcessorMonitor(queue, service);				
		var monitorThread = new Thread(monitor);
		monitorThread.start();			
		assertTrue(monitorThread.isAlive());

		queue.poll();
		await()
			.atMost(Duration.ofSeconds(2))
		.with()
			.until(monitor.isTerminated());
		
		assertFalse(monitorThread.isAlive());
		assertTrue(service.isTerminated());
		
	}

}

package br.com.fileSplitter.file;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Test;

import br.com.fileSplitter.file.model.Index;

class ProcessorMonitorTest {

	
	@Test
	void shouldInterruptWhenQueueIsNull() throws InterruptedException {
		
		Queue<Index> queue = null;
		ExecutorService service = Executors.newFixedThreadPool(1);
		
		ProcessorMonitor monitor = new ProcessorMonitor(queue, service);
				
		var monitorThread = new Thread(monitor);
		monitorThread.start();	
		
		Thread.sleep(500);
	
		assertFalse(monitorThread.isAlive());		
		
	}
	
	
	
	@Test
	void shouldInterruptWhenExecutorsIsNull() throws InterruptedException {
		
		Queue<Index> queue = new LinkedBlockingQueue<>();
		ExecutorService service = null;
		
		ProcessorMonitor monitor = new ProcessorMonitor(queue, service);
				
		var monitorThread = new Thread(monitor);
		monitorThread.start();	
		
		Thread.sleep(500);
	
		assertFalse(monitorThread.isAlive());		
		
	}
	
	@Test
	void shouldBeRunningWhenStillHaveProcessRunning() throws InterruptedException {
		
		Queue<Index> queue = new LinkedBlockingQueue<>();
		ExecutorService service = Executors.newFixedThreadPool(1);
		
		ProcessorMonitor monitor = new ProcessorMonitor(queue, service);
		var monitorThread = new Thread(monitor);
		monitorThread.start();	
		
		for (int i = 0; i < 2; i++) {
			service.execute(()->{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {				
					Thread.currentThread().interrupt();
				}
			});
		}
			
		assertFalse(service.isTerminated());		
		
	}
	
	@Test
	void shouldBeRunningWhenQueueIsNotEmpty() throws InterruptedException {
		
		Queue<Index> queue = new LinkedBlockingQueue<>();
		queue.add(new Index(null,null,null));
		ExecutorService service = Executors.newFixedThreadPool(1);
		
		ProcessorMonitor monitor = new ProcessorMonitor(queue, service);
		var monitorThread = new Thread(monitor);
		monitorThread.start();	
	
		service.execute(()->{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {				
				Thread.currentThread().interrupt();
			}
		});
				
		assertFalse(service.isTerminated());
		assertTrue(monitorThread.isAlive());
		
	}
	
	@Test
	void shouldInterruptWhenQueueIsEmpty() throws InterruptedException {
		
		Queue<Index> queue = new LinkedBlockingQueue<>();
		queue.add(new Index(null,null,null));
		ExecutorService service = Executors.newFixedThreadPool(1);		
		ProcessorMonitor monitor = new ProcessorMonitor(queue, service);
			
		service.execute(()->{			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {				
				Thread.currentThread().interrupt();
			}
		});
				
		var monitorThread = new Thread(monitor);
		monitorThread.start();	
		System.out.println(monitorThread.isAlive());
		assertTrue(monitorThread.isAlive());
		queue.poll();
		Thread.sleep(2500);
		System.out.println(monitorThread.isAlive());
		System.out.println(service.isTerminated());
		
		assertFalse(monitorThread.isAlive());
		assertTrue(service.isTerminated());
		
	}
	

}

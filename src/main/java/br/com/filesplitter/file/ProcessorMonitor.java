package br.com.filesplitter.file;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.filesplitter.file.model.Index;

public class ProcessorMonitor implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessorMonitor.class);
	
private final Queue<Index> queue;
private final ExecutorService executor;
	
	public ProcessorMonitor(Queue<Index> queue, ExecutorService executorService){
		this.queue = queue;
		this.executor = executorService;
	}
	
	@Override
	public void run() {
			queueMonitor(queue);
	}
	
	
	private void queueMonitor(Queue<Index> queue) {
		
		
		
		
		LOG.info("Monitor started...");
		try {
			while (!queue.isEmpty()) {
				Thread.sleep(5000);
			}			
		} catch (NullPointerException | InterruptedException e) {
			
			shutdown(executor);
			Thread.currentThread().interrupt();
		}
		//Once all itens of the queue are polled, we start the Executor shutdown and
		// the awaitTermination to terminate only after all tasks are finished
		
		shutdown(executor);
		
		LOG.info("Monitor finished...");
	}
	
	private void shutdown(ExecutorService executor) {
		try {
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.MINUTES);
			
			while(!executor.isTerminated()) {
				Thread.sleep(500);
			}
			
			LOG.info("All the tasks are finished!");
			
		} catch (NullPointerException |InterruptedException e) {
			 Thread.currentThread().interrupt();
		}
	}

	public Callable<Boolean> isTerminated(){

		if(executor!=null)
			return () -> Boolean.valueOf(executor.isTerminated());
		else
			return () -> Boolean.valueOf(true);

		
	}

}

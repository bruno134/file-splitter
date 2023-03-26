package br.com.fileSplitter.file;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		
		while(queue.size()>0) {
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		
		//Once all itens of the queue are polled, we start the Executor shutdown and
		// the awaitTermination to terminate only after all tasks are finished
		
		try {
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.MINUTES);
			
			while(!executor.isTerminated()) {
				Thread.sleep(500);
			}
			
			LOG.info("All the tasks are finished!");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		LOG.info("Monitor finished...");
	}

}

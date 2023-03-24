package br.com.fileSplitter.file;

import java.time.LocalDateTime;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueMonitor implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(QueueMonitor.class);
	
private final Queue<Index> queue;
	
	public QueueMonitor(Queue<Index> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
			queueMonitor(queue);
	}
	
	
	private void queueMonitor(Queue<Index> queue) {
		
		Integer queueQuantity = 0;
		
		System.out.println(LocalDateTime.now() + " monitor started");
		
		while((queueQuantity = queue.size())>0) {
			
			LOG.info(String.format("%d left...", queueQuantity));
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}

package br.com.fileSplitter;
import java.io.IOException;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fileSplitter.file.reader.RandomBlockFileReader;
import br.com.fileSplitter.file.reader.RandomBlockFileReaderException;
import br.com.fileSplitter.file.writer.FileWriterException;
import br.com.fileSplitter.file.writer.SplitterFileWriter;

public class SplitterFileWorker implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(SplitterFileWorker.class);
	private Queue<int[]> queue;
	private final Integer retry;
	private String filename;
	private Long sleepTime = 500L;

	public SplitterFileWorker(String filename, Queue<int[]> queue) {
		this.queue = queue;
		this.retry = 5;
		this.filename = filename;
	}

	public SplitterFileWorker(String filename, Queue<int[]> queue, Integer retry) {
		this.queue = queue;
		this.retry = retry;
		this.filename = filename;
	}

	@Override
	public void run() {
		try {
			readIndexesFromQueue(queue);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RandomBlockFileReaderException e) {
			e.printStackTrace();
		} catch (FileWriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readIndexesFromQueue(Queue<int[]> queue) throws IOException, RandomBlockFileReaderException, FileWriterException {

		int[] item;
		int retry = 0;

		// TODO Must be provider by dependency
		RandomBlockFileReader reader = new RandomBlockFileReader();
		SplitterFileWriter writer = new SplitterFileWriter();

		while ((item = queue.poll()) != null || retry <= this.retry) {

			
			if (item != null) {
				
				StringBuilder sb = new StringBuilder();
				// TODO I don't have here how to know the size of line.
				sb = reader.readBlock(filename, 1000, item[0], item[1]);
				
				//TODO how to set source file name and destination file name
				if(sb!=null || sb.length()>0) {
					writer.writeAndSaveFile(sb, item[0], item[1]);
				}				
				
			} else {

				LOG.info("Sleeping %d", sleepTime);
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				retry++;
			}

			LOG.info("Terminating the pooling");
		}

	}

}

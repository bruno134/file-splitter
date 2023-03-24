package br.com.fileSplitter.file;

import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fileSplitter.file.reader.SplitterReader;
import br.com.fileSplitter.file.writer.SplitterWriter;

public class SplitterFileWorker implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(SplitterFileWorker.class);

	private SplitterReader splitterReader;
	private SplitterWriter splitterWriter;
	private Queue<Index> queue;
	
	public SplitterFileWorker(SplitterWriter writer, SplitterReader reader, Queue<Index> queue) {

		this.splitterWriter = writer;
		this.splitterReader = reader;
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			readIndexesFromQueue();
		} catch (SplitterFileException e) {		 
			e.printStackTrace();
		}
	}

	public void readIndexesFromQueue() throws SplitterFileException {
				
		if (splitterWriter == null)
			throw new SplitterFileException("Writer must be defined");

		if (splitterReader == null)
			throw new SplitterFileException("Reader must be defined");
		
		Long sleepTime = 500L;	
		
		Index index;
		int qtyRetry = 0;
		
		while ((index = queue.poll()) != null || qtyRetry <= 10) {

			if (index != null) {

				StringBuilder fileInput;
				
				fileInput = splitterReader.read(index);

				
				if (fileInput != null | fileInput.length() > 0) {
					splitterWriter.write(fileInput, index.getFileNumber());
				}
				
				qtyRetry = 0;

			} else {

//				LOG.info(String.format("Sleeping %d ms...", sleepTime));
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}

				qtyRetry++;
			}
		}
		LOG.info("Terminating the pooling");
	}

}

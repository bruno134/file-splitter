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
	private SplitterFileConfiguration configuration;
	private Queue<Index> queue;
	private Integer retry = 5;
	private Long sleepTime = 500L;

	public SplitterFileWorker(SplitterFileConfiguration configuration, SplitterWriter writer, SplitterReader reader)
			throws SplitterFileException {

		if (configuration == null)
			throw new SplitterFileException("Configuration not set");

		if ("".equals(configuration.getSourceFile()))
			throw new SplitterFileException("Source file name not set");

		if (writer == null)
			throw new SplitterFileException("Writer must be defined");

		if (reader == null)
			throw new SplitterFileException("Reader must be defined");

		this.splitterWriter = writer;
		this.splitterReader = reader;
		this.configuration = configuration;
		this.queue = (Queue<Index>) configuration.getQueue();
		this.retry = configuration.getRetry() != null ?configuration.getRetry():this.retry;
		this.sleepTime = configuration.getSleepTime() != null ?configuration.getSleepTime():this.sleepTime;

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

		Index index;
		int retry = 0;

		while ((index = queue.poll()) != null || retry <= this.retry) {

			if (index != null) {

				StringBuilder fileInput;

				fileInput = splitterReader.read(index, configuration.getSourceFile());

				// TODO how to set source file name and destination file name
				if (fileInput != null || fileInput.length() > 0) {
					splitterWriter.write(fileInput, configuration);
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

package br.com.fileSplitter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fileSplitter.file.FileUtils;
import br.com.fileSplitter.file.Index;
import br.com.fileSplitter.file.ProcessorMonitor;
import br.com.fileSplitter.file.SplitterFileConfiguration;
import br.com.fileSplitter.file.SplitterFileException;
import br.com.fileSplitter.file.SplitterFileIndexer;
import br.com.fileSplitter.file.SplitterFileWorker;
import br.com.fileSplitter.file.reader.RandomBlockFileReader;
import br.com.fileSplitter.file.writer.SplitterFileWriter;

public class Splitter {

	private static final Logger LOG = LoggerFactory.getLogger(Splitter.class);
	private SplitterFileConfiguration config;
	
	public Splitter(SplitterFileConfiguration config) {
		super();
		this.config = config;
	}
	
	public void splitAndWrite(String sourceFileName, String destinationFileName) throws SplitterFileException {

		Queue<Index> queue = new ConcurrentLinkedQueue<>();
		List<Index> indexes = new ArrayList<Index>();
		File sourceFile = new File(sourceFileName);

		LOG.info("Indexing file");
		indexes = new SplitterFileIndexer(sourceFile).mapIndexes(config.getMarkers());

		LOG.info(String.format("Founded %d indexes", indexes.size()));

		for (Index index : indexes) {
			queue.add(index);
		}
				
		LOG.info("File(s) will be saved in " + FileUtils.returnFilePath(destinationFileName));
		
		ExecutorService executor = getExecutorService();

		if (config.getMonitor())
			startMonitor(queue, executor);

		Index index;

		while ((index = queue.poll()) != null) {
			executor.execute(new SplitterFileWorker(new SplitterFileWriter(config),
													new RandomBlockFileReader(sourceFile), index));
		}
	}
	private void startMonitor(Queue<Index> queue, ExecutorService executorService) {
		new Thread(new ProcessorMonitor(queue, executorService)).start();
		
	}
	
	private ExecutorService getExecutorService() {
		
		ExecutorService executor = new ThreadPoolExecutor(config.getThreadPool(), 50, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		
		return executor;
	}
}

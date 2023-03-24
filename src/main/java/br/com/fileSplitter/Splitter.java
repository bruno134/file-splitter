package br.com.fileSplitter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fileSplitter.file.Index;
import br.com.fileSplitter.file.QueueMonitor;
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
		SplitterFileWriter writer;
		RandomBlockFileReader reader;
			
		File sourceFile = new File(sourceFileName);
		
		LOG.info("Indexing file");
		indexes = new SplitterFileIndexer(sourceFile).mapIndexes(config.getMarkers());
		
		
		LOG.info(String.format("Founded %d indexes", indexes.size()));
		
		for(Index index: indexes) {
			queue.add(index);
		}
		LOG.info("working on file(s)");
		
		reader = new RandomBlockFileReader(sourceFile);
		writer = new SplitterFileWriter(config);
			
		SplitterFileWorker worker = new SplitterFileWorker(writer, reader, queue);
		
		if(queue.size()>=500)
			startMonitor(queue);
		
		for (int i = 0; i < config.getThreadPool(); i++) {
			new Thread(worker).start();
		}	
		
		LOG.info("End of process...");

	}
	
	private void startMonitor(Queue<Index> queue) {
		new Thread(new QueueMonitor(queue)).start();
		
	}
	
}

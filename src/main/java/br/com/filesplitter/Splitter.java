package br.com.filesplitter;

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

import br.com.filesplitter.file.ProcessorMonitor;
import br.com.filesplitter.file.SplitterFileException;
import br.com.filesplitter.file.SplitterFileIndexer;
import br.com.filesplitter.file.SplitterFileWorker;
import br.com.filesplitter.file.model.Index;
import br.com.filesplitter.file.model.SplitterFileConfiguration;
import br.com.filesplitter.file.reader.RandomBlockFileReader;
import br.com.filesplitter.file.util.FileUtils;
import br.com.filesplitter.file.writer.SplitterFileWriter;

public class Splitter {

	private static final Logger LOG = LoggerFactory.getLogger(Splitter.class);
	private static final String DEFAULT_FILENAME = "splitted_file";
	private static final String DEFAULT_FILE_EXTENSION = ".txt";
	private static final Integer DEFAULT_THREAD_POOL = 1;
	private SplitterFileConfiguration config;
	
	public Splitter(SplitterFileConfiguration config) {
		super();
		this.config = config;
	}
	
	public void splitAndWrite(String sourceFileName, String targetFilePath) throws SplitterFileException {

		Queue<Index> queue = new ConcurrentLinkedQueue<>();
		List<Index> indexes;
		File sourceFile = new File(sourceFileName);

		LOG.info("Indexing file");
		indexes = new SplitterFileIndexer(sourceFile).mapIndexes(config.getMarkers());
		
		LOG.info("Founded {} indexes", indexes.size());
		
		for (Index index : indexes) {
			queue.add(index);
		}
				
		LOG.info("File(s) will be saved in: {} ", targetFilePath);
		
		ExecutorService executor = getExecutorService();

		startMonitor(queue, executor);

		Index index;		

		while ((index = queue.poll()) != null) {
			
			File writeFile = FileUtils.createFile(buildFilePathWithFileAndIndex(targetFilePath, index.getFileNumber()));
			
			executor.execute(new SplitterFileWorker(new SplitterFileWriter(writeFile),
													new RandomBlockFileReader(sourceFile), index));
		}
	}
	private void startMonitor(Queue<Index> queue, ExecutorService executorService) {
		new Thread(new ProcessorMonitor(queue, executorService)).start();
		
	}
	
	private ExecutorService getExecutorService() {
		
		Integer threadPool = config.getThreadPool()!=null?config.getThreadPool():DEFAULT_THREAD_POOL;
				
		return new ThreadPoolExecutor(threadPool, 50, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		
		
	}
	
	private String buildFilePathWithFileAndIndex(String targetFilePath, Integer fileNumber) {
		
		String fileName = config.getOutputFileName()!=null?config.getOutputFileName():DEFAULT_FILENAME;
		String extension = config.getOutputFileExtension()!=null?config.getOutputFileExtension():DEFAULT_FILE_EXTENSION;
		String divisor = "";
				
		if(targetFilePath.charAt(targetFilePath.length()-1) != '/') {
			divisor = "/";
		}
				
		return  targetFilePath+divisor+fileName+"_"+fileNumber+extension;
	}
	
}

package br.com.filesplitter.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.filesplitter.file.model.Index;
import br.com.filesplitter.file.model.Pointer;
import br.com.filesplitter.file.reader.SplitterReader;
import br.com.filesplitter.file.util.FileUtils;
import br.com.filesplitter.file.writer.SplitterFileWriter;
import br.com.filesplitter.file.writer.SplitterWriter;

class SplitterFileWorkerTest {

	private static final String MOCK_CONTENT = "@@HEADER999TRAILLER";
	private static final String SRC_TEST_RESOURCES_WORKER_FILE_TXT = "src/test/resources/workerFile.txt";
	private final FakeWriter writer = new FakeWriter();
	private final FakeReader reader = new FakeReader();
	private final Pointer pointer = new Pointer(0,1);
	private final Index index = new Index(1,pointer,pointer);
	
	@Test
	void shouldReadAndWrite() throws SplitterFileException, IOException {
		
		File targetFile = FileUtils.createFile(SRC_TEST_RESOURCES_WORKER_FILE_TXT);
		SplitterFileWriter writer = new SplitterFileWriter(targetFile);
		
		SplitterFileWorker worker =  new SplitterFileWorker(writer, reader, index);
		
		worker.run();
		
		assertTrue(targetFile.exists());
		
		BufferedReader reader = new BufferedReader(new FileReader(targetFile));
	    String currentLine = reader.readLine();
	    reader.close();
		
	    assertEquals(MOCK_CONTENT, currentLine);
	    targetFile.delete();
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenReaderIsNull() throws SplitterFileException, IOException {
		
		
		SplitterFileWorker worker =  new SplitterFileWorker(writer, null, index);
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			worker.readIndexesFromQueue();	
		});

		assertEquals(SplitterFileWorker.READER_MUST_BE_DEFINED, exception.getMessage());
		
	}
	
	@Test
	void shouldRaiseCustomExceptionWhenWriterIsNull() throws SplitterFileException, IOException {
		
		
		SplitterFileWorker worker =  new SplitterFileWorker(null, reader, index);
		
		SplitterFileException exception = assertThrows(SplitterFileException.class, () -> {
			worker.readIndexesFromQueue();	
		});

		assertEquals(SplitterFileWorker.WRITER_MUST_BE_DEFINED, exception.getMessage());
		
	}
	
	
	
	
	
	
	private class FakeReader implements SplitterReader{

		@Override
		public StringBuilder read(Index index) throws SplitterFileException {
			return new StringBuilder(MOCK_CONTENT);
		}
		
	}
	
	private class FakeWriter implements SplitterWriter{
		private final Logger LOG = LoggerFactory.getLogger(FakeWriter.class);
		@Override
		public void write(StringBuilder input) throws SplitterFileException {
			LOG.info("writing the input: " + input);
		}

		
	}
	
}

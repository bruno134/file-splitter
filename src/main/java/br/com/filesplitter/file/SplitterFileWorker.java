package br.com.filesplitter.file;

import br.com.filesplitter.file.model.Index;
import br.com.filesplitter.file.reader.SplitterReader;
import br.com.filesplitter.file.writer.SplitterWriter;

public class SplitterFileWorker implements Runnable {

	public static final String READER_MUST_BE_DEFINED = "Reader must be defined";
	public static final String WRITER_MUST_BE_DEFINED = "Writer must be defined";
	private SplitterReader splitterReader;
	private SplitterWriter splitterWriter;
	private Index index;

	public SplitterFileWorker(SplitterWriter writer, SplitterReader reader, Index index) {

		this.splitterWriter = writer;
		this.splitterReader = reader;
		this.index = index;
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
			throw new SplitterFileException(WRITER_MUST_BE_DEFINED);

		if (splitterReader == null)
			throw new SplitterFileException(READER_MUST_BE_DEFINED);

		StringBuilder fileInput;

		fileInput = splitterReader.read(index);

		if (fileInput != null && fileInput.length() > 0) {

			splitterWriter.write(fileInput);

		}

	}

}

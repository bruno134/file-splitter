package br.com.fileSplitter.file;

import br.com.fileSplitter.file.model.Index;
import br.com.fileSplitter.file.reader.SplitterReader;
import br.com.fileSplitter.file.writer.SplitterWriter;

public class SplitterFileWorker implements Runnable {

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
			throw new SplitterFileException("Writer must be defined");

		if (splitterReader == null)
			throw new SplitterFileException("Reader must be defined");

		StringBuilder fileInput;

		fileInput = splitterReader.read(index);

		if (fileInput != null | fileInput.length() > 0) {

			splitterWriter.write(fileInput);

		}

	}

}

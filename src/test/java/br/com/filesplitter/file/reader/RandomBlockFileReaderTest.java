package br.com.filesplitter.file.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import br.com.filesplitter.file.SplitterFileException;
import br.com.filesplitter.file.model.Index;
import br.com.filesplitter.file.model.Pointer;

class RandomBlockFileReaderTest {

	private RandomBlockFileReader reader;
	private Index index;
	private final String originalFilename = "src/test/resources/XS-test-file-1000.txt";

	@Test
	void shouldReturnTextBlockWhenIndexAndFileAreProvided() {
		try {

			index = new Index(1, new Pointer(1, 0), new Pointer(10, 9009));
			File file = new File(originalFilename);
			reader = new RandomBlockFileReader(file);

			StringBuilder sb = reader.read(index);

			assertEquals(10010, sb.length());
			assertTrue(sb.toString().startsWith("@@H"));
			assertEquals('9', sb.toString().charAt(10008));

		} catch (SplitterFileException e) {
			e.printStackTrace();
		}
	}

	@Test
	void shouldRaiseErrorWhenIndexIsNull() {

		File file = new File(originalFilename);
		reader = new RandomBlockFileReader(file);

		SplitterFileException readerException = assertThrows(SplitterFileException.class, () -> {
			reader.read(index);
		});

		assertEquals(RandomBlockFileReader.INDEX_WAS_NOT_PROVIDED, readerException.getMessage());

	}
}

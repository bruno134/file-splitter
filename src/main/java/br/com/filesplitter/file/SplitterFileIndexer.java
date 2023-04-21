package br.com.filesplitter.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.filesplitter.file.model.Index;
import br.com.filesplitter.file.model.MarkerEnum;
import br.com.filesplitter.file.model.Pointer;
import br.com.filesplitter.file.util.FileUtils;

public class SplitterFileIndexer {

	private static final String READ = "r";
	private final File sourceFile;

	public SplitterFileIndexer(File sourceFile) throws SplitterFileException {

		FileUtils.fileValidate(sourceFile);

		this.sourceFile = sourceFile;
	}

	public SplitterFileIndexer(String sourceFile) throws SplitterFileException {

		this.sourceFile = FileUtils.createFile(sourceFile);
	}

	public List<Index> mapIndexes(Map<String, MarkerEnum> markers) throws SplitterFileException {

		if(markers==null)
			throw new SplitterFileException("The markers should be provided");
		
		List<Index> indexes = new ArrayList<>();
		int fileCount = 0;
		int pointerPosition = 0;
		int linePosition	= 1;
		int charReaded     = 0;
		int[] indexHeader   = new int[2];
		int[] indexTrailler = new int[2];
		MarkerEnum pointerFound;

		try (RandomAccessFile reader = new RandomAccessFile(sourceFile, READ)) {

			reader.seek(pointerPosition);
			var firstLineAux = reader.readLine();

			/**
			 * This is necessary to find out the type of End of line. An EOL can consist of
			 * up to two char controls the possibilities are: windows => CR+LF UNIX => LF
			 * Machintosh => CR
			 */
			reader.seek(firstLineAux.length());
			var eolSize = reader.readLine().length() + 1;

			// Then, we define the line size as quantity chars + eol chars + 1 to jump to
			// the next line.
			var lineSize = firstLineAux.length() + eolSize;
			charReaded = firstLineAux.charAt(0);

			while (charReaded >= 0) {

				pointerFound = markers.getOrDefault(String.valueOf((char)charReaded), MarkerEnum.BODY);

				switch (pointerFound) {
				case HEADER:
					indexHeader[0] = linePosition;
					indexHeader[1] = pointerPosition;
					break;

				case TRAILLER:
					indexTrailler[0] = linePosition;
					indexTrailler[1] = pointerPosition;
					break;
					
				default:
					break;
				}

				if (indexHeader[0] > 0 && indexTrailler[0] > 0) {
					fileCount++;
					
					indexes.add(new Index(fileCount, new Pointer(indexHeader[0], indexHeader[1]),
							new Pointer(indexTrailler[0], indexTrailler[1])));

					indexHeader[0] = 0;
					indexHeader[1] = 0;
					indexTrailler[0] = 0;
					indexTrailler[1] = 0;
				}

				reader.seek(pointerPosition += lineSize);
				charReaded = reader.read();
				linePosition++;
			}

		} catch (IOException e) {
			throw new SplitterFileException(e.getMessage());
		} 

		return indexes;
	}

}

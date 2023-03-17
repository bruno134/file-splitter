package br.com.fileSplitter.file.reader;

public class RandomBlockFileReaderException extends Exception {

	private static final long serialVersionUID = 2119869211268640370L;
	private String message;
	
	public RandomBlockFileReaderException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}

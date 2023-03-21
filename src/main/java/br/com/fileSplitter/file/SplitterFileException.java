package br.com.fileSplitter.file;

public class SplitterFileException extends Exception {

	private static final long serialVersionUID = 1409254898223697182L;
	private String message;
	
	public SplitterFileException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

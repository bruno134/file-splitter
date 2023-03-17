package br.com.fileSplitter.file;

public class SplitterFileIndexerException extends Exception {

	private static final long serialVersionUID = 1409254898223697182L;
	private String message;
	
	public SplitterFileIndexerException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

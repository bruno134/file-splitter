package br.com.filesplitter.file;

public class SplitterFileException extends Exception {

	private static final long serialVersionUID = 1409254898223697182L;
	private final String message;
	
	public SplitterFileException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}

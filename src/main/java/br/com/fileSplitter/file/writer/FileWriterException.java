package br.com.fileSplitter.file.writer;

public class FileWriterException extends Exception {

	private static final long serialVersionUID = -8469783650175664536L;
	private final String message;
	
	
	
	
	public FileWriterException(String message) {
		super();
		this.message = message;
	}




	public String getMessage() {
		return message;
	}
	
	
	
}

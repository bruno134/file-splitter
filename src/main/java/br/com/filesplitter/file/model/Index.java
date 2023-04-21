package br.com.filesplitter.file.model;

public class Index {

	private final Integer fileNumber;
	private final Pointer header;
	private final Pointer trailler;
	
	public Index(Integer fileNumber, Pointer header, Pointer trailler) {
		this.fileNumber = fileNumber;
		this.header = header;
		this.trailler = trailler;
	}
	public Pointer getHeader() {
		return header;
	}
	public Pointer getTrailler() {
		return trailler;
	}
	public Integer getFileNumber() {
		return fileNumber;
	}
	
	@Override
	public String toString() {
		return "Index [fileNumber=" + fileNumber + ", header=" + header + ", trailler=" + trailler + "]";
	}
	

	
	
}

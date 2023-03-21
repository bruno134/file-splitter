package br.com.fileSplitter.file;

public class Index {

	private final Pointer header;
	private final Pointer trailler;
	
	public Index(Pointer header, Pointer trailler) {
		
		this.header = header;
		this.trailler = trailler;
	}
	public Pointer getHeader() {
		return header;
	}
	public Pointer getTrailler() {
		return trailler;
	}
	
	
	@Override
	public String toString() {
		return "Index [header=" + header + ", trailler=" + trailler + "]";
	}
	
	
	
}

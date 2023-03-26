package br.com.fileSplitter.file.model;

public class Pointer {

	private final Integer linePosition;
	private final Integer pointerPosition;
	
	
	public Pointer(Integer linePosition, Integer pointerPosition) {
		super();
		this.linePosition = linePosition;
		this.pointerPosition = pointerPosition;
	}


	public Integer getLinePosition() {
		return linePosition;
	}


	public Integer getPointerPosition() {
		return pointerPosition;
	}


	@Override
	public String toString() {
		return "Index [linePosition=" + linePosition + ", pointerPosition=" + pointerPosition + "]";
	}

}

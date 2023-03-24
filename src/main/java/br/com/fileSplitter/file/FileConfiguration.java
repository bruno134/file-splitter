package br.com.fileSplitter.file;



public class FileConfiguration {
	private String fileName;
	private String fileExtension;	
	private static final String DEFAULT_EXTENSION = ".txt";


	private FileConfiguration(Builder builder) {
		this.fileName = builder.fileName;
		this.fileExtension = builder.fileExtension;		
	}
	
	public String getFileName() {
		return fileName;
	}
	public String getFileExtension() {
		
		if("".equals(fileExtension) | fileExtension==null)
			return DEFAULT_EXTENSION;
		
		return fileExtension;
	}

	
	public String getFullFile() {
		return fileName+getFileExtension();
	}
		
	public static Builder builder() {
		return new Builder();
	}
	

	public static final class Builder {
		private String fileName;
		private String fileExtension;
		

		private Builder() {
		}

		public Builder withFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public Builder withFileExtension(String fileExtension) {
			this.fileExtension = fileExtension;
			return this;
		}

		public FileConfiguration build() {
			return new FileConfiguration(this);
		}
	}
	
	
	
	
}

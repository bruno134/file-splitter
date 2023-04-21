package br.com.filesplitter.file.model;

import java.util.Map;

public class SplitterFileConfiguration {

	private Map<String,MarkerEnum> markers;
	private Integer threadPool;
	private String outputFileName;
	private String outputFileExtension;
	
	
	private SplitterFileConfiguration(Builder builder) {
		
		this.threadPool = builder.threadPool;
		this.outputFileName = builder.outputFileName;
		this.outputFileExtension = builder.outputFileExtension;		
		this.markers = builder.markers;
	}
	
	
	public String getOutputFileName() {
		return outputFileName;
	}

	public String getOutputFileExtension() {
		return outputFileExtension;
	}
	
	public Map<String, MarkerEnum> getMarkers() {
		return markers;
	}
	
	public static Builder builder(Map<String,MarkerEnum> markers) {
		return new Builder(markers);
	}

	
	public Integer getThreadPool() {
		return threadPool;
	}

	
	public static final class Builder {
		private Map<String,MarkerEnum> markers;
		private Integer threadPool= 0;
		private String outputFileName;
		private String outputFileExtension;


		private Builder(Map<String,MarkerEnum> markers) {
			this.markers = markers;
		}

		public Builder withOutputFileName(String outputFileName) {
			this.outputFileName = outputFileName;
			return this;
		}
		
		public Builder withOutputFileExtension(String outputFileExtension) {
			this.outputFileExtension = outputFileExtension;
			return this;
		}
		
		public Builder withThreadPool(Integer threadPool) {
			this.threadPool = threadPool;
			return this;
		}
		
		public SplitterFileConfiguration build() {
			return new SplitterFileConfiguration(this);
		}
	}


	

	
	
}
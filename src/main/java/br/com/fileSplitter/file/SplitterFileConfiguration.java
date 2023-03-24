package br.com.fileSplitter.file;

import java.util.Map;

public class SplitterFileConfiguration {

	private Map<String,MarkerEnum> markers;
	private Integer threadPool;
	private FileConfiguration fileConfiguration;
	
	
	private SplitterFileConfiguration(Builder builder) {
		this.markers = builder.markers;
		this.threadPool = builder.threadPool;
		this.fileConfiguration = builder.fileConfiguration;
	}
	
	
	public Map<String, MarkerEnum> getMarkers() {
		return markers;
	}

	public FileConfiguration getFileConfiguration() {
		return fileConfiguration;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public Integer getThreadPool() {
		return threadPool;
	}

	
	public static final class Builder {
		private Map<String,MarkerEnum> markers;
		private Integer threadPool= 0;
		private FileConfiguration fileConfiguration;

		private Builder() {
		}

		public Builder withFileConfiguration(FileConfiguration fileConfiguration) {
			this.fileConfiguration = fileConfiguration;
			return this;
		}

		public Builder withMarkers(Map<String,MarkerEnum> markers) {
			this.markers = markers;
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
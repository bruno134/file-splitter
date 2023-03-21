package br.com.fileSplitter.file;

import java.util.Queue;


public class SplitterFileConfiguration {

		
	private final String sourceFile;
	private Integer retry;
	private Long sleepTime;
	private Queue<?> queue;
	private FileConfiguration fileConfiguration;

	
	private SplitterFileConfiguration(Builder builder) {
		this.sourceFile = builder.sourceFile;
		this.retry = builder.retry;
		this.sleepTime = builder.sleepTime;
		this.queue = builder.queue;
		this.fileConfiguration = builder.fileConfiguration;
	}
	
	public String getSourceFile() {
		return sourceFile;
	}

	public Integer getRetry() {
		return retry;
	}

	public Long getSleepTime() {
		return sleepTime;
	}

	public Queue<?> getQueue() {
		return queue;
	}

	public FileConfiguration getFileConfiguration() {
		return fileConfiguration;
	}

	
	public static Builder builder() {
		return new Builder();
	}

	
	public static final class Builder {
		private String sourceFile;
		private Integer retry;
		private Long sleepTime;
		private Queue<?> queue;
		private FileConfiguration fileConfiguration;

		private Builder() {
		}

		public Builder withSourceFile(String sourceFile) {
			this.sourceFile = sourceFile;
			return this;
		}

		public Builder withRetry(Integer retry) {
			this.retry = retry;
			return this;
		}

		public Builder withSleepTime(Long sleepTime) {
			this.sleepTime = sleepTime;
			return this;
		}

		public Builder withQueue(Queue<?> queue) {
			this.queue = queue;
			return this;
		}

		public Builder withFileConfiguration(FileConfiguration fileConfiguration) {
			this.fileConfiguration = fileConfiguration;
			return this;
		}

		public SplitterFileConfiguration build() {
			return new SplitterFileConfiguration(this);
		}
	}

	
	
}
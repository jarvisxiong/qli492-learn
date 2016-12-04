package com.lianjia.trace.util;

public class HttpSpanCollectorConfig {
	private final int connectTimeout;
	private final int readTimeout;
	private final int flushInterval;
	private final boolean compressionEnabled;

	private final static int default_Connect_Timeout = 10 * 1000;
	private final static int default_read_Timeout = 60 * 1000;
	private final static int default_flush_Interval = 1;
	private final static boolean default_compression_Enabled = false;

	private HttpSpanCollectorConfig() {
		this(default_Connect_Timeout, default_read_Timeout, default_flush_Interval, default_compression_Enabled);
	}

	private HttpSpanCollectorConfig(int connectTimeout, int readTimeout, int flushInterval,
			boolean compressionEnabled) {
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
		this.flushInterval = flushInterval;
		this.compressionEnabled = compressionEnabled;
	}
	
	public static HttpSpanCollectorConfig create() {
		return new HttpSpanCollectorConfig();
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public int getFlushInterval() {
		return flushInterval;
	}

	public boolean isCompressionEnabled() {
		return compressionEnabled;
	}
}

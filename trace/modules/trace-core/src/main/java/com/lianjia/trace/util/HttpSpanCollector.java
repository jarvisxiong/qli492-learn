package com.lianjia.trace.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

import com.lianjia.trace.collector.AbstractSpanCollector;
import com.lianjia.trace.handler.SpanCollectorMetricsHandler;

public class HttpSpanCollector extends AbstractSpanCollector {
	private final String url;
	private final HttpSpanCollectorConfig config;

	public static HttpSpanCollector create(String baseUrl, SpanCollectorMetricsHandler metrics) {
		return new HttpSpanCollector(baseUrl, HttpSpanCollectorConfig.create(), metrics);
	}

	public static HttpSpanCollector create(String baseUrl, HttpSpanCollectorConfig config,
			SpanCollectorMetricsHandler metrics) {
		return new HttpSpanCollector(baseUrl, config, metrics);
	}

	public HttpSpanCollector(String baseUrl, SpanCollectorMetricsHandler metrics) {
		this(baseUrl, HttpSpanCollectorConfig.create(), metrics);
	}

	public HttpSpanCollector(String baseUrl, HttpSpanCollectorConfig config, SpanCollectorMetricsHandler metrics) {
		super(SpanCodec.JSON, metrics, config.getFlushInterval());
		this.url = baseUrl + (baseUrl.endsWith("/") ? "" : "/") + "api/v1/spans";
		this.config = config;
	}

	@Override
	protected void sendSpans(byte[] json) throws IOException {
		// intentionally not closing the connection, so as to use keep-alives
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setConnectTimeout(config.getConnectTimeout());
		connection.setReadTimeout(config.getReadTimeout());
		connection.setRequestMethod("POST");
		connection.addRequestProperty("Content-Type", "application/json");
		if (config.isCompressionEnabled()) {
			connection.addRequestProperty("Content-Encoding", "gzip");
			ByteArrayOutputStream gzipped = new ByteArrayOutputStream();
			try (GZIPOutputStream compressor = new GZIPOutputStream(gzipped)) {
				compressor.write(json);
			}
			json = gzipped.toByteArray();
		}
		connection.setDoOutput(true);
		connection.setFixedLengthStreamingMode(json.length);
		connection.getOutputStream().write(json);

		try (InputStream in = connection.getInputStream()) {
			while (in.read() != -1)
				; // skip
		} catch (IOException e) {
			try (InputStream err = connection.getErrorStream()) {
				if (err != null) { // possible, if the connection was dropped
					while (err.read() != -1)
						; // skip
				}
			}
			throw e;
		}
	}
}
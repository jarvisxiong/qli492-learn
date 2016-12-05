package com.lianjia.trace.collector;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.lianjia.trace.Span;
import com.lianjia.trace.codec.SpanCodec;
import com.lianjia.trace.handler.SpanCollectorDefaultMetricsHander;
import com.lianjia.trace.handler.SpanCollectorMetricsHandler;
import com.lianjia.trace.util.JsonUtil;

public class HttpSpanCollector extends AbstractSpanCollector {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String url;
	private final HttpSpanCollectorConfig config;

	public static HttpSpanCollector create(String baseUrl, SpanCollectorMetricsHandler metrics) {
		return new HttpSpanCollector(baseUrl, HttpSpanCollectorConfig.create(), metrics);
	}

	public static HttpSpanCollector create(String baseUrl, HttpSpanCollectorConfig config, SpanCollectorMetricsHandler metrics) {
		return new HttpSpanCollector(baseUrl, config, metrics);
	}

	public HttpSpanCollector(String baseUrl) {
		this(baseUrl, HttpSpanCollectorConfig.create(), SpanCollectorDefaultMetricsHander.create());
	}

	public HttpSpanCollector(String baseUrl, SpanCollectorMetricsHandler metrics) {
		this(baseUrl, HttpSpanCollectorConfig.create(), metrics);
	}

	public HttpSpanCollector(String baseUrl, HttpSpanCollectorConfig config, SpanCollectorMetricsHandler metrics) {
		super(SpanCodec.JSON, metrics, config.getFlushInterval());
		this.url = baseUrl + (baseUrl.endsWith("/") ? "" : "/") + "api/span";
		this.config = config;
	}

	@Override
	protected void sendSpans(byte[] json) throws IOException {
		// HttpURLConnection connection = (HttpURLConnection) new
		// URL(url).openConnection();
		// connection.setConnectTimeout(config.getConnectTimeout());
		// connection.setReadTimeout(config.getReadTimeout());
		// connection.setRequestMethod("POST");
		// connection.addRequestProperty("Content-Type", "application/json");
		// if (config.isCompressionEnabled()) {
		// connection.addRequestProperty("Content-Encoding", "gzip");
		// ByteArrayOutputStream gzipped = new ByteArrayOutputStream();
		// try (GZIPOutputStream compressor = new GZIPOutputStream(gzipped)) {
		// compressor.write(json);
		// }
		// json = gzipped.toByteArray();
		// }
		// connection.setDoOutput(true);
		// connection.setFixedLengthStreamingMode(json.length);
		// connection.getOutputStream().write(json);
		//
		// try (InputStream in = connection.getInputStream()) {
		// while (in.read() != -1)
		// ; // skip
		// } catch (IOException e) {
		// try (InputStream err = connection.getErrorStream()) {
		// if (err != null) { // possible, if the connection was dropped
		// while (err.read() != -1)
		// ; // skip
		// }
		// }
		// throw e;
		// }
		TypeReference<List<Span>> type = new TypeReference<List<Span>>() {};
		List<Span> spans = JsonUtil.byte2Object(json, type);
		logger.info(JSON.toJSONString(spans, true));
	}
}
package com.lianjia.trace.interceptor;

import java.util.logging.Logger;

import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.Span;
import com.lianjia.trace.TraceData;
import com.lianjia.trace.adapter.ServerRequestAdapter;
import com.lianjia.trace.tracer.ServerTracer;
import com.lianjia.trace.util.Util;

public class ServerRequestInterceptor {
	private final static Logger LOGGER = Logger.getLogger(ServerRequestInterceptor.class.getName());

	private final ServerTracer serverTracer;

	public ServerRequestInterceptor(ServerTracer serverTracer) {
		this.serverTracer = Util.checkNotNull(serverTracer, "Null serverTracer");
	}

	public void handle(ServerRequestAdapter adapter) {
		serverTracer.clearCurrentSpan();
		final TraceData traceData = adapter.getTraceData();
		Boolean sample = traceData.getSample();
		if (sample != null && Boolean.FALSE.equals(sample)) {
			serverTracer.setStateNoTracing();
			LOGGER.fine("Received indication that we should NOT trace.");
		} else {
			if (traceData.getSpan() != null) {
				LOGGER.fine("Received span information as part of request.");
				Span span = traceData.getSpan();
				serverTracer.setStateCurrentTrace(span.getTraceId(), span.getId(), span.getParentId(), span.getName());
			} else {
				LOGGER.fine("Received no span state.");
				serverTracer.setStateUnknown(adapter.getSpanName());
			}
			serverTracer.setServerReceived();
			for (KeyValueAnnotation annotation : adapter.requestAnnotations()) {
				serverTracer.submitBinaryAnnotation(annotation.getKey(), annotation.getValue());
			}
		}
	}
}

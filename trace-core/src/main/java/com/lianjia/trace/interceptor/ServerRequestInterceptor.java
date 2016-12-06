package com.lianjia.trace.interceptor;

import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.Span;
import com.lianjia.trace.TraceData;
import com.lianjia.trace.adapter.ServerRequestAdapter;
import com.lianjia.trace.tracer.ServerTracer;
import com.lianjia.trace.util.Util;

public class ServerRequestInterceptor {
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
		} else {
			if (traceData.getSpan() != null) {
				Span span = traceData.getSpan();
				serverTracer.setStateCurrentTrace(span.getTraceId(), span.getId(), span.getParentId(), span.getName());
			} else {
				serverTracer.setStateUnknown(adapter.getSpanName());
			}
			serverTracer.setServerReceived();
			for (KeyValueAnnotation annotation : adapter.requestAnnotations()) {
				serverTracer.submitBinaryAnnotation(annotation.getKey(), annotation.getValue());
			}
		}
	}
}

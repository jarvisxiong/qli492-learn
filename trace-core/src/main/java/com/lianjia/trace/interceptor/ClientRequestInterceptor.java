package com.lianjia.trace.interceptor;

import com.lianjia.trace.Endpoint;
import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.Span;
import com.lianjia.trace.adapter.ClientRequestAdapter;
import com.lianjia.trace.tracer.ClientTracer;
import com.lianjia.trace.util.Util;

public class ClientRequestInterceptor {
	private final ClientTracer clientTracer;

	public ClientRequestInterceptor(ClientTracer clientTracer) {
		this.clientTracer = Util.checkNotNull(clientTracer, "Null clientTracer");
	}

	public void handle(ClientRequestAdapter adapter) {
		Span span = clientTracer.startNewSpan(adapter.getTraceId(), adapter.getSpanName(), adapter.isSampled());
		if (span == null) {
			adapter.addSpanToRequest(null);
		} else {
			adapter.addSpanToRequest(span);
			for (KeyValueAnnotation annotation : adapter.requestAnnotations()) {
				clientTracer.submitBinaryAnnotation(annotation.getKey(), annotation.getValue());
			}
			recordClientSentAnnotations(adapter.serverAddress());
		}
	}

	private void recordClientSentAnnotations(Endpoint serverAddress) {
		if (serverAddress == null) {
			clientTracer.setClientSent();
		} else {
			clientTracer.setClientSent(serverAddress.getIp(), serverAddress.getPort(), serverAddress.getServiceName());
		}
	}
}

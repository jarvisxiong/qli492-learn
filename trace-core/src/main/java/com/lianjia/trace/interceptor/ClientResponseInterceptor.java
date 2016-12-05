package com.lianjia.trace.interceptor;

import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.adapter.ClientResponseAdapter;
import com.lianjia.trace.tracer.ClientTracer;
import com.lianjia.trace.util.Util;

public class ClientResponseInterceptor {
	private final ClientTracer clientTracer;

	public ClientResponseInterceptor(ClientTracer clientTracer) {
		this.clientTracer = Util.checkNotNull(clientTracer, "Null clientTracer");
	}

	public void handle(ClientResponseAdapter adapter) {
		try {
			for (KeyValueAnnotation annotation : adapter.responseAnnotations()) {
				clientTracer.submitBinaryAnnotation(annotation.getKey(), annotation.getValue());
			}
		} finally {
			clientTracer.setClientReceived();
		}
	}
}
package com.lianjia.trace.interceptor;

import java.util.logging.Logger;

import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.adapter.ServerResponseAdapter;
import com.lianjia.trace.tracer.ServerTracer;
import com.lianjia.trace.util.Util;

public class ServerResponseInterceptor {

	private final static Logger LOGGER = Logger.getLogger(ServerResponseInterceptor.class.getName());

	private final ServerTracer serverTracer;

	public ServerResponseInterceptor(ServerTracer serverTracer) {
		this.serverTracer = Util.checkNotNull(serverTracer, "Null serverTracer");
	}

	public void handle(ServerResponseAdapter adapter) {
		LOGGER.fine("Sending server send.");
		try {
			for (KeyValueAnnotation annotation : adapter.responseAnnotations()) {
				serverTracer.submitBinaryAnnotation(annotation.getKey(), annotation.getValue());
			}
			serverTracer.setServerSend();
		} finally {
			serverTracer.clearCurrentSpan();
		}
	}
}
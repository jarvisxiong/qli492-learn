package com.lianjia.trace.tracer;

import com.lianjia.trace.AnnotationSubmitter;
import com.lianjia.trace.ServerSpanAndEndpoint;
import com.lianjia.trace.Span;
import com.lianjia.trace.collector.SpanCollector;
import com.lianjia.trace.constants.CoreConstants;
import com.lianjia.trace.sampler.Sampler;
import com.lianjia.trace.util.SpanIdGenerator;

public class ServerTracer extends AnnotationSubmitter {
	private final ServerSpanAndEndpoint spanAndEndpoint;
	private final SpanIdGenerator spanIdGenerator;
	private final SpanCollector spanCollector;
	private final Sampler traceSampler;

	private ServerTracer(ServerSpanAndEndpoint spanAndEndpoint, SpanIdGenerator spanIdGenerator, SpanCollector spanCollector, Sampler traceSampler) {
		if (spanAndEndpoint == null) {
			throw new NullPointerException("Null spanAndEndpoint");
		}
		this.spanAndEndpoint = spanAndEndpoint;
		if (spanIdGenerator == null) {
			throw new NullPointerException("Null randomGenerator");
		}
		this.spanIdGenerator = spanIdGenerator;
		if (spanCollector == null) {
			throw new NullPointerException("Null spanCollector");
		}
		this.spanCollector = spanCollector;
		if (traceSampler == null) {
			throw new NullPointerException("Null traceSampler");
		}
		this.traceSampler = traceSampler;
	}

	public static ServerTracer create(ServerSpanAndEndpoint spanAndEndpoint, SpanIdGenerator spanIdGenerator, SpanCollector spanCollector,
			Sampler traceSampler) {
		return new ServerTracer(spanAndEndpoint, spanIdGenerator, spanCollector, traceSampler);
	}

	public void clearCurrentSpan() {
		getSpanAndEndpoint().state().setCurrentServerSpan(null);
	}

	public void setStateCurrentTrace(String traceId, String spanId, String parentSpanId, String name) {
		getSpanAndEndpoint().state().setCurrentServerSpan(Span.create(traceId, spanId, parentSpanId, name));
	}

	public void setStateNoTracing() {
		getSpanAndEndpoint().state().setCurrentServerSpan(null);
	}

	public void setStateUnknown(String spanName) {
		String newTraceId = getRandomSpanIdGenerator().getSpanId();
		if (!getTraceSampler().isSampled(newTraceId)) {
			getSpanAndEndpoint().state().setCurrentServerSpan(null);
			return;
		}
		getSpanAndEndpoint().state().setCurrentServerSpan(Span.create(newTraceId, newTraceId, null, spanName));
	}

	public void setServerReceived() {
		submitStartAnnotation(CoreConstants.SERVER_RECV);
	}

	public void setServerReceived(String ip, int port, String clientService) {
		submitAddress(CoreConstants.CLIENT_ADDR, ip, port, clientService);
		submitStartAnnotation(CoreConstants.SERVER_RECV);
	}

	public void setServerSend() {
		if (submitEndAnnotation(CoreConstants.SERVER_SEND, getSpanCollector())) {
			getSpanAndEndpoint().state().setCurrentServerSpan(null);
		}
	}

	@Override
	public ServerSpanAndEndpoint getSpanAndEndpoint() {
		return spanAndEndpoint;
	}

	public SpanIdGenerator getRandomSpanIdGenerator() {
		return spanIdGenerator;
	}

	public SpanCollector getSpanCollector() {
		return spanCollector;
	}

	public Sampler getTraceSampler() {
		return traceSampler;
	}
}

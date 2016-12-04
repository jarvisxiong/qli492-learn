package com.lianjia.trace.tracer;

import com.lianjia.trace.AnnotationSubmitter;
import com.lianjia.trace.ClientSpanAndEndpoint;
import com.lianjia.trace.ServerSpan;
import com.lianjia.trace.Span;
import com.lianjia.trace.SpanId;
import com.lianjia.trace.collector.SpanCollector;
import com.lianjia.trace.constants.CoreConstants;
import com.lianjia.trace.sampler.Sampler;
import com.lianjia.trace.util.SpanIdGenerator;

public class ClientTracer extends AnnotationSubmitter {
	private final ClientSpanAndEndpoint spanAndEndpoint;
	private final SpanIdGenerator spanIdGenerator;
	private final SpanCollector spanCollector;
	private final Sampler traceSampler;

	private ClientTracer(ClientSpanAndEndpoint spanAndEndpoint, SpanIdGenerator spanIdGenerator, SpanCollector spanCollector, Sampler traceSampler) {
		if (spanAndEndpoint == null) {
			throw new NullPointerException("Null spanAndEndpoint");
		}
		this.spanAndEndpoint = spanAndEndpoint;
		if (spanIdGenerator == null) {
			throw new NullPointerException("Null spanIdGenerator");
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

	public static ClientTracer create(ClientSpanAndEndpoint spanAndEndpoint, SpanIdGenerator spanIdGenerator, SpanCollector spanCollector,
			Sampler traceSampler) {
		return new ClientTracer(spanAndEndpoint, spanIdGenerator, spanCollector, traceSampler);
	}

	public void setClientSent() {
		submitStartAnnotation(CoreConstants.CLIENT_SEND);
	}

	public void setClientSent(String ip, int port, String serviceName) {
		submitAddress(CoreConstants.SERVER_ADDR, ip, port, serviceName);
		submitStartAnnotation(CoreConstants.CLIENT_SEND);
	}

	public void setClientReceived() {
		if (submitEndAnnotation(CoreConstants.CLIENT_RECV, getSpanCollector())) {
			getSpanAndEndpoint().getState().setCurrentClientSpan(null);
		}
	}

	public SpanId startNewSpan(String traceId, String spanName, boolean isSampled) {
		if (isSampled) {
			SpanId newSpanId = getNewSpanId(traceId);
			Span newSpan = newSpanId.toSpan();
			newSpan.setName(spanName);
			getSpanAndEndpoint().getState().setCurrentClientSpan(newSpan);
			return newSpanId;
		}
		Boolean sample = getSpanAndEndpoint().getState().sample();
		if (Boolean.FALSE.equals(sample)) {
			getSpanAndEndpoint().getState().setCurrentClientSpan(null);
			return null;
		}
		SpanId newSpanId = getNewSpanId(traceId);
		if (sample == null) {
			if (!getTraceSampler().isSampled(newSpanId.traceId)) {
				getSpanAndEndpoint().getState().setCurrentClientSpan(null);
				return null;
			}
		}
		Span newSpan = newSpanId.toSpan();
		newSpan.setName(spanName);
		getSpanAndEndpoint().getState().setCurrentClientSpan(newSpan);
		return newSpanId;
	}

	private SpanId getNewSpanId(String traceId) {
		Span parentSpan = getSpanAndEndpoint().getState().getCurrentLocalSpan();
		if (parentSpan == null) {
			ServerSpan serverSpan = getSpanAndEndpoint().getState().getCurrentServerSpan();
			if (serverSpan != null) {
				parentSpan = serverSpan.getSpan();
			}
		}
		String newSpanId = getSpanIdGenerator().getSpanId();
		if (parentSpan == null) {
			return SpanId.create(traceId, newSpanId);
		}
		return SpanId.create(parentSpan.getTraceId(), parentSpan.getId(), newSpanId);
	}

	@Override
	public ClientSpanAndEndpoint getSpanAndEndpoint() {
		return spanAndEndpoint;
	}
	
	public SpanIdGenerator getSpanIdGenerator() {
		return spanIdGenerator;
	}

	public SpanCollector getSpanCollector() {
		return spanCollector;
	}

	public Sampler getTraceSampler() {
		return traceSampler;
	}
}

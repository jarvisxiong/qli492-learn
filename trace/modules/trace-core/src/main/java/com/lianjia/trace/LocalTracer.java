package com.lianjia.trace;

import com.lianjia.trace.collector.SpanCollector;
import com.lianjia.trace.sampler.Sampler;
import com.lianjia.trace.util.SpanIdGenerator;

public final class LocalTracer extends AnnotationSubmitter {
	private final LocalSpanAndEndpoint spanAndEndpoint;
	private final SpanIdGenerator spanIdGenerator;
	private final SpanCollector spanCollector;
	private final Sampler traceSampler;

	private LocalTracer(LocalSpanAndEndpoint spanAndEndpoint, SpanIdGenerator spanGenerator, SpanCollector spanCollector, Sampler traceSampler) {
		if (spanAndEndpoint == null) {
			throw new NullPointerException("Null spanAndEndpoint");
		}
		this.spanAndEndpoint = spanAndEndpoint;
		if (spanGenerator == null) {
			throw new NullPointerException("Null randomGenerator");
		}
		this.spanIdGenerator = spanGenerator;
		if (spanCollector == null) {
			throw new NullPointerException("Null spanCollector");
		}
		this.spanCollector = spanCollector;
		if (traceSampler == null) {
			throw new NullPointerException("Null traceSampler");
		}
		this.traceSampler = traceSampler;
	}

	public static LocalTracer create(LocalSpanAndEndpoint spanAndEndpoint, SpanIdGenerator spanIdGenerator, SpanCollector spanCollector,
			Sampler traceSampler) {
		return new LocalTracer(spanAndEndpoint, spanIdGenerator, spanCollector, traceSampler);
	}

	@Override
	public LocalSpanAndEndpoint getSpanAndEndpoint() {
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

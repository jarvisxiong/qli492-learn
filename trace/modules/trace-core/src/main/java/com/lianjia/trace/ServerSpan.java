package com.lianjia.trace;

import com.lianjia.trace.util.Util;

public abstract class ServerSpan {

	public static final ServerSpan EMPTY = new DefaultServerSpan(null, null);
	public static final ServerSpan NOT_SAMPLED = new DefaultServerSpan(null, false);

	public abstract Span getSpan();

	public abstract Boolean getSample();

	public static ServerSpan create(Span span) {
		return create(Util.checkNotNull(span, "span"), true);
	}

	public static ServerSpan create(Span span, Boolean sample) {
		return new DefaultServerSpan(span, sample);
	}

	public static ServerSpan create(String traceId, String spanId, String parentSpanId, String name) {
		Span span = new Span();
		span.setTraceId(traceId);
		span.setId(spanId);
		if (parentSpanId != null) {
			span.setParentId(parentSpanId);
		}
		span.setName(name);
		return create(span, true);
	}

	public static ServerSpan create(final Boolean sample) {
		return create(null, sample);
	}

	ServerSpan() {
	}
}

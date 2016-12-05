package com.lianjia.trace;

import com.lianjia.trace.util.Util;

public final class TraceData {
	private final Span span;
	private final Boolean sample;

	private TraceData(Span span, Boolean sample) {
		this.span = span;
		this.sample = sample;
	}

	public static TraceData create(Span span, Boolean sample) {
		return new TraceData(Util.checkNotNull(span, "span"), sample);
	}

	public static TraceData create(Boolean sample) {
		return create(null, sample);
	}
	
	public static TraceData create() {
		return create(null, null);
	}

	public Span getSpan() {
		return span;
	}

	public Boolean getSample() {
		return sample;
	}
}

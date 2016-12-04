package com.lianjia.trace;

import com.lianjia.trace.util.Util;

public final class TraceData {
	private final SpanId spanId;
	private final Boolean sample;

	private TraceData(SpanId spanId, Boolean sample) {
		this.spanId = spanId;
		this.sample = sample;
	}

	public static TraceData create(SpanId spanId, Boolean sample) {
		return create(Util.checkNotNull(spanId, "spanId"), sample);
	}

	public static TraceData create(Boolean sample) {
		return create(null, sample);
	}
	
	public static TraceData create() {
		return create(null, null);
	}

	public SpanId getSpanId() {
		return spanId;
	}

	public Boolean getSample() {
		return sample;
	}
}

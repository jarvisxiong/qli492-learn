package com.lianjia.trace;

public final class SpanId {
	public final String traceId;
	public final String parentId;
	public final String spanId;

	public SpanId(String traceId, String parentId, String spanId) {
		this.traceId = (parentId == traceId) ? parentId : traceId;
		this.parentId = (parentId == spanId) ? traceId : parentId;
		this.spanId = spanId;
	}

	public static SpanId create(String traceId, String spanId) {
		return new SpanId(traceId, null, spanId);
	}

	public static SpanId create(String traceId, String parentId, String spanId) {
		return new SpanId(traceId, parentId, spanId);
	}

	public String nullableParentId() {
		return root() ? null : parentId;
	}

	public final boolean root() {
		return parentId == null;
	}

	public Span toSpan() {
		Span result = new Span();
		result.setId(spanId);
		result.setTraceId(traceId);
		result.setParentId(nullableParentId());
		return result;
	}
}

package com.lianjia.trace.codec;

import java.util.List;

import com.lianjia.trace.Span;

public final class DefaultSpanCodec implements SpanCodec {
	public static final SpanCodec JSON = new DefaultSpanCodec(Codec.JSON);

	private final Codec codec;

	private DefaultSpanCodec(Codec codec) {
		this.codec = codec;
	}

	@Override
	public byte[] writeSpan(Span span) {
		return codec.writeSpan(span);
	}

	@Override
	public byte[] writeSpans(List<Span> spans) {
		return codec.writeSpans(spans);
	}

	@Override
	public Span readSpan(byte[] bytes) {
		Span result = codec.readSpan(bytes);
		return result;
	}
}

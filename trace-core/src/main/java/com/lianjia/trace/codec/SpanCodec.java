package com.lianjia.trace.codec;

import java.util.List;

import com.lianjia.trace.Span;

public interface SpanCodec {
	  SpanCodec JSON = DefaultSpanCodec.JSON;

	  byte[] writeSpan(Span span);

	  byte[] writeSpans(List<Span> spans);

	  Span readSpan(byte[] bytes);
}

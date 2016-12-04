package com.lianjia.trace.util;

import java.util.List;

import com.lianjia.trace.Span;

public interface SpanCodec {
	  SpanCodec THRIFT = DefaultSpanCodec.THRIFT;
	  SpanCodec JSON = DefaultSpanCodec.JSON;

	  byte[] writeSpan(Span span);

	  byte[] writeSpans(List<Span> spans);

	  /** throws {@linkplain IllegalArgumentException} if the span couldn't be decoded */
	  Span readSpan(byte[] bytes);
}

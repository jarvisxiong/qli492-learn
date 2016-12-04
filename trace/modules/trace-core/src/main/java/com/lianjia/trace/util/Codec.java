package com.lianjia.trace.util;

import java.util.List;

import com.lianjia.trace.Span;

public interface Codec {
	JsonCodec JSON = new JsonCodec();

	Span readSpan(byte[] bytes);

	byte[] writeSpan(Span value);

	List<Span> readSpans(byte[] bytes);

	byte[] writeSpans(List<Span> value);
}

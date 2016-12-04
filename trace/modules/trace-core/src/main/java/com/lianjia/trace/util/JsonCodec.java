package com.lianjia.trace.util;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.lianjia.trace.Span;

public final class JsonCodec implements Codec {
	@Override
	public Span readSpan(byte[] bytes) {
		Util.checkArgument(bytes.length > 0, "Empty input reading Span");
		return JsonUtil.byte2Object(bytes, Span.class);
	}

	@Override
	public byte[] writeSpan(Span value) {
		return JsonUtil.object2Byte(value);
	}

	@Override
	public List<Span> readSpans(byte[] bytes) {
		Util.checkArgument(bytes.length > 0, "Empty input reading List<Span>");
		TypeReference<List<Span>> type = new TypeReference<List<Span>>() {
		};
		return JsonUtil.byte2Object(bytes, type);
	}

	@Override
	public byte[] writeSpans(List<Span> value) {
		return JsonUtil.object2Byte(value);
	}
}

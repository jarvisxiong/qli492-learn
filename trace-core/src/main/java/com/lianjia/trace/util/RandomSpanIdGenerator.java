package com.lianjia.trace.util;

import java.util.Random;

public class RandomSpanIdGenerator implements SpanIdGenerator {
	final Random random = new Random();

	@Override
	public String getSpanId(String traceId) {
		SpanNumHolder.plusNum(traceId);
		return random.nextLong() + "";
	}

	@Override
	public String getSpanNumPath(String traceId, String lastPath) {
		return (lastPath != null ? lastPath : 0) + "." + SpanNumHolder.getNum(traceId);
	}
}

package com.lianjia.trace.util;

import java.util.Random;

public class RandomSpanIdGenerator implements SpanIdGenerator {
	final Random random = new Random();
	
	@Override
	public String getSpanId() {
		return random.nextLong() + "";
	}
}

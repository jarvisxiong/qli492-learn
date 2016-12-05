package com.lianjia.trace.handler;

import java.util.concurrent.atomic.AtomicInteger;

public class SpanCollectorDefaultMetricsHander implements SpanCollectorMetricsHandler {
	final AtomicInteger acceptedSpans = new AtomicInteger();
	final AtomicInteger droppedSpans = new AtomicInteger();

	@Override
	public void incrementAcceptedSpans(int quantity) {
		acceptedSpans.addAndGet(quantity);
	}

	@Override
	public void incrementDroppedSpans(int quantity) {
		droppedSpans.addAndGet(quantity);
	}
}

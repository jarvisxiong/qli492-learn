package com.lianjia.trace.handler;

public interface SpanCollectorMetricsHandler {

	void incrementAcceptedSpans(int quantity);

	void incrementDroppedSpans(int quantity);

}
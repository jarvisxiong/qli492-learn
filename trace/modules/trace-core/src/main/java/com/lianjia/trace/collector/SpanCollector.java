package com.lianjia.trace.collector;

import com.lianjia.trace.Span;

public interface SpanCollector {
	
	void collect(final Span span);
	
}

package com.lianjia.trace.util;

public interface SpanIdGenerator {
	
	String getSpanId(String traceId);
	
	String getSpanNumPath(String traceId, String lastPath);
}

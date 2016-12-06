package com.lianjia.trace.adapter;

import java.util.Collection;

import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.TraceData;

public interface ServerRequestAdapter {

	TraceData getTraceData();
	
	String getSpanName();

	Collection<KeyValueAnnotation> requestAnnotations();
}
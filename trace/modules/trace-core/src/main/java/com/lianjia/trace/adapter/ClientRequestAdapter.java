package com.lianjia.trace.adapter;

import java.util.Collection;

import com.lianjia.trace.Endpoint;
import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.SpanId;

public interface ClientRequestAdapter {
	
	String getTraceId();

	String getSpanName();
	
	boolean isSampled();

	void addSpanIdToRequest(SpanId spanId);

	Collection<KeyValueAnnotation> requestAnnotations();

	Endpoint serverAddress();
}
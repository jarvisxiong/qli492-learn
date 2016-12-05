package com.lianjia.trace.adapter;

import java.util.Collection;

import com.lianjia.trace.Endpoint;
import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.Span;

public interface ClientRequestAdapter {
	
	String getTraceId();

	String getSpanName();
	
	boolean isSampled();

	void addSpanToRequest(Span span);

	Collection<KeyValueAnnotation> requestAnnotations();

	Endpoint serverAddress();
}
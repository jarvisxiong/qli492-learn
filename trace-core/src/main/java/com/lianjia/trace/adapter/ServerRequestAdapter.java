package com.lianjia.trace.adapter;

import java.util.Collection;

import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.TraceData;

/**
 * Provides properties needed for dealing with server request.
 */
public interface ServerRequestAdapter {

	TraceData getTraceData();
	
	String getSpanName();

	Collection<KeyValueAnnotation> requestAnnotations();
}
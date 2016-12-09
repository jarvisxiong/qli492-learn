package com.lianjia.trace.adapter;

import java.util.Collection;

import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.TraceData;

public interface ServerRequestAdapter {

	void setTraceIdAndNodePath(String serviceName);

	TraceData getTraceData();

	String getSpanName();

	Collection<KeyValueAnnotation> requestAnnotations();
}
package com.lianjia.trace.adapter;

import java.util.Collection;

import com.lianjia.trace.KeyValueAnnotation;

public interface ServerResponseAdapter {

	Collection<KeyValueAnnotation> responseAnnotations();
}

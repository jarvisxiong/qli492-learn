package com.lianjia.trace.adapter;

import java.util.Collection;

import com.lianjia.trace.KeyValueAnnotation;

public interface ClientResponseAdapter {

	Collection<KeyValueAnnotation> responseAnnotations();

}

package com.lianjia.trace.adapter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.alibaba.dubbo.rpc.Result;
import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.adapter.ClientResponseAdapter;

public class DubboClientResponseAdapter implements ClientResponseAdapter {
	private Result rpcResult;
	private Exception exception;

	public DubboClientResponseAdapter(Exception exception) {
		this.exception = exception;
	}

	public DubboClientResponseAdapter(Result rpcResult) {
		this.rpcResult = rpcResult;
	}

	@Override
	public Collection<KeyValueAnnotation> responseAnnotations() {
		List<KeyValueAnnotation> annotations = new ArrayList<KeyValueAnnotation>();
		if (exception != null) {
			KeyValueAnnotation keyValueAnnotation = KeyValueAnnotation.create("exception", exception.getMessage());
			annotations.add(keyValueAnnotation);
		} else {
			if (rpcResult.hasException()) {
				KeyValueAnnotation keyValueAnnotation = KeyValueAnnotation.create("exception", rpcResult.getException().getMessage());
				annotations.add(keyValueAnnotation);
			} else {
				KeyValueAnnotation keyValueAnnotation = KeyValueAnnotation.create("status", "success");
				annotations.add(keyValueAnnotation);
			}
		}
		return annotations;
	}

}

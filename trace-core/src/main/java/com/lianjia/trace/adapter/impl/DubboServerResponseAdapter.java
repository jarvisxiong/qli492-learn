package com.lianjia.trace.adapter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.alibaba.dubbo.rpc.Result;
import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.adapter.ServerResponseAdapter;
import com.lianjia.trace.constants.CoreConstants;

public class DubboServerResponseAdapter implements ServerResponseAdapter {
	private Result rpcResult;

	public DubboServerResponseAdapter(Result rpcResult) {
		this.rpcResult = rpcResult;
	}

	@Override
	public Collection<KeyValueAnnotation> responseAnnotations() {
		List<KeyValueAnnotation> annotations = new ArrayList<KeyValueAnnotation>();
		if (!rpcResult.hasException()) {
			KeyValueAnnotation keyValueAnnotation = KeyValueAnnotation.create(CoreConstants.SERVER_RESULT, "true");
			annotations.add(keyValueAnnotation);
			// TODO Obejct Size
			keyValueAnnotation = KeyValueAnnotation.create(CoreConstants.PACKET_SIZE, "0");
			annotations.add(keyValueAnnotation);
		} else {
			KeyValueAnnotation keyValueAnnotation = KeyValueAnnotation.create(CoreConstants.EXCEPTION, rpcResult.getException().getMessage());
			annotations.add(keyValueAnnotation);
		}
		return annotations;
	}
}

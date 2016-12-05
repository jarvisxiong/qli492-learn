package com.lianjia.trace.dubbo.support;

import com.alibaba.dubbo.rpc.RpcContext;
import com.lianjia.trace.dubbo.DubboClientNameProvider;

public class DefaultClientNameProvider implements DubboClientNameProvider {
	@Override
	public String resolveClientName(RpcContext rpcContext) {
		String application = RpcContext.getContext().getAttachment("clientName");
		return application;
	}
}

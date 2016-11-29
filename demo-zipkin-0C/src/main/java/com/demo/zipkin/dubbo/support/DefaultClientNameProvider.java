package com.demo.zipkin.dubbo.support;

import com.alibaba.dubbo.rpc.RpcContext;
import com.demo.zipkin.dubbo.DubboClientNameProvider;

public class DefaultClientNameProvider implements DubboClientNameProvider {
	@Override
	public String resolveClientName(RpcContext rpcContext) {
		String application = RpcContext.getContext().getAttachment("clientName");
		return application;
	}
}

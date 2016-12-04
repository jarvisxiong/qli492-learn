package com.demo.zipkin.dubbo.support;

import com.alibaba.dubbo.rpc.RpcContext;
import com.lianjia.trace.dubbo.DubboSpanNameProvider;

public class DefaultSpanNameProvider implements DubboSpanNameProvider {
	@Override
	public String resolveSpanName(RpcContext rpcContext) {
		String className = rpcContext.getUrl().getPath();
		String simpleName = className.substring(className.lastIndexOf(".") + 1);
		return simpleName + "." + rpcContext.getMethodName();
	}
}

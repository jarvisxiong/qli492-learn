package com.demo.zipkin.dubbo.support;

import com.alibaba.dubbo.rpc.RpcContext;
import com.demo.zipkin.dubbo.DubboServerNameProvider;

public class DefaultServerNameProvider implements DubboServerNameProvider {
	@Override
	public String resolveServerName(RpcContext rpcContext) {
		String interfaceName = rpcContext.getUrl().getPath();
		String serverName = interfaceName.substring(interfaceName.lastIndexOf(".") + 1);
		return serverName;
	}
}

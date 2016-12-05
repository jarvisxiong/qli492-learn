package com.lianjia.trace.dubbo.support;

import com.alibaba.dubbo.rpc.RpcContext;
import com.lianjia.trace.dubbo.DubboServerNameProvider;

public class DefaultServerNameProvider implements DubboServerNameProvider {
	@Override
	public String resolveServerName(RpcContext rpcContext) {
		String interfaceName = rpcContext.getUrl().getPath();
		String serverName = interfaceName.substring(interfaceName.lastIndexOf(".") + 1);
		return serverName;
	}
}

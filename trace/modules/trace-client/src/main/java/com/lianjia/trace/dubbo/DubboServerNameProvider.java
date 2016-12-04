package com.lianjia.trace.dubbo;

import com.alibaba.dubbo.rpc.RpcContext;

public interface DubboServerNameProvider {
	public String resolveServerName(RpcContext rpcContext);
}

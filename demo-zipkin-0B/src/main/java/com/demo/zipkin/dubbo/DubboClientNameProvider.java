package com.demo.zipkin.dubbo;

import com.alibaba.dubbo.rpc.RpcContext;

public interface DubboClientNameProvider {
	public String resolveClientName(RpcContext rpcContext);
}

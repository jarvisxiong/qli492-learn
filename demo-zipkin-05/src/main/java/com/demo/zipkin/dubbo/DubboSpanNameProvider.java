package com.demo.zipkin.dubbo;

import com.alibaba.dubbo.rpc.RpcContext;

public interface DubboSpanNameProvider {
	public String resolveSpanName(RpcContext rpcContext);
}

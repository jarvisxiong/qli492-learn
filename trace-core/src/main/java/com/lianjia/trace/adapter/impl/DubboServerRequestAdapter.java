package com.lianjia.trace.adapter.impl;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcContext;
import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.Span;
import com.lianjia.trace.TraceData;
import com.lianjia.trace.adapter.ServerRequestAdapter;
import com.lianjia.trace.tracer.ServerTracer;

@SuppressWarnings("unused")
public class DubboServerRequestAdapter implements ServerRequestAdapter {
	private Invoker<?> invoker;
	private Invocation invocation;
	private ServerTracer serverTracer;

	public DubboServerRequestAdapter(Invoker<?> invoker, Invocation invocation, ServerTracer serverTracer) {
		this.invoker = invoker;
		this.invocation = invocation;
		this.serverTracer = serverTracer;
	}
	

	@Override
	public String getSpanName() {
		String path = RpcContext.getContext().getUrl().getPath();
		String simpleName = path.substring(path.lastIndexOf(".") + 1);
		String spanName = simpleName + "." + RpcContext.getContext().getMethodName();
		return spanName;
	}

	@Override
	public TraceData getTraceData() {
		String sampled = invocation.getAttachment("sampled");
		if (sampled != null && sampled.equals("0")) {
			return TraceData.create(false);
		} else {
			final String parentId = invocation.getAttachment("parentId");
			final String spanId = invocation.getAttachment("spanId");
			final String traceId = invocation.getAttachment("traceId");
			if (traceId != null && spanId != null) {
				Span span = Span.create(traceId, spanId, parentId, getSpanName());
				return TraceData.create(span, true);
			}
		}
		return TraceData.create();
	}

	@Override
	public Collection<KeyValueAnnotation> requestAnnotations() {
		String ipAddr = RpcContext.getContext().getUrl().getIp();
		InetSocketAddress inetSocketAddress = RpcContext.getContext().getRemoteAddress();
		final String clientName = RpcContext.getContext().getAttachment("clientName");
		// 标记服务器收到,记录客户端地址，服务名，生成ca节点
		// serverTracer.setServerReceived(IPConversion.convertToInt(ipAddr),
		// inetSocketAddress.getPort(), clientName);
		InetSocketAddress socketAddress = RpcContext.getContext().getLocalAddress();
		if (socketAddress != null) {
			KeyValueAnnotation remoteAddrAnnotation = KeyValueAnnotation.create("address", socketAddress.toString());
			return Collections.singleton(remoteAddrAnnotation);
		} else {
			return Collections.emptyList();
		}
	}
}

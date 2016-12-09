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
import com.lianjia.trace.constants.CoreConstants;
import com.lianjia.trace.constants.UrlConstants;
import com.lianjia.trace.tracer.ServerTracer;
import com.lianjia.trace.util.TraceIdAndNodePathHolder;

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
	public void setTraceIdAndNodePath(String serviceName) {
		String traceId = invocation.getAttachment(UrlConstants.LJ_TRACEID);
		String nodePath = invocation.getAttachment(UrlConstants.LJ_NODEPATH);
		TraceIdAndNodePathHolder.setTraceIdAndNodePath(traceId, nodePath + "." + serviceName);
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
		String sampled = invocation.getAttachment(UrlConstants.LJ_SAMPLED);
		if (sampled != null && sampled.equals("0")) {
			return TraceData.create(false);
		} else {
			final String parentId = invocation.getAttachment(UrlConstants.LJ_PARENTSPANID);
			final String spanId = invocation.getAttachment(UrlConstants.LJ_SPANID);
			final String traceId = invocation.getAttachment(UrlConstants.LJ_TRACEID);
			final String numPath = invocation.getAttachment(UrlConstants.LJ_NUMPATH);
			if (traceId != null && spanId != null) {
				Span span = Span.create(traceId, spanId, parentId, numPath, getSpanName());
				return TraceData.create(span, true);
			}
		}
		return TraceData.create();
	}

	@Override
	public Collection<KeyValueAnnotation> requestAnnotations() {
		String ipAddr = RpcContext.getContext().getUrl().getIp();
		InetSocketAddress inetSocketAddress = RpcContext.getContext().getRemoteAddress();
		final String clientName = RpcContext.getContext().getAttachment(UrlConstants.LJ_CLIENTNAME);
		InetSocketAddress socketAddress = RpcContext.getContext().getLocalAddress();
		if (socketAddress != null) {
			KeyValueAnnotation remoteAddrAnnotation = KeyValueAnnotation.create(CoreConstants.ADDRESS, socketAddress.toString());
			return Collections.singleton(remoteAddrAnnotation);
		} else {
			return Collections.emptyList();
		}
	}
}

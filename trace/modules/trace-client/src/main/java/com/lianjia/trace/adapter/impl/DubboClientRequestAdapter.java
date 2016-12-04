package com.lianjia.trace.adapter.impl;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcContext;
import com.demo.zipkin.dubbo.support.DefaultServerNameProvider;
import com.demo.zipkin.dubbo.support.DefaultSpanNameProvider;
import com.lianjia.trace.Endpoint;
import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.SpanId;
import com.lianjia.trace.adapter.ClientRequestAdapter;
import com.lianjia.trace.constants.CoreConstants;
import com.lianjia.trace.dubbo.DubboServerNameProvider;
import com.lianjia.trace.dubbo.DubboSpanNameProvider;

@SuppressWarnings("unused")
public class DubboClientRequestAdapter implements ClientRequestAdapter {
	private Invoker<?> invoker;
	private Invocation invocation;
	private final static DubboSpanNameProvider spanNameProvider = new DefaultSpanNameProvider();
	private final static DubboServerNameProvider serverNameProvider = new DefaultServerNameProvider();

	public DubboClientRequestAdapter(Invoker<?> invoker, Invocation invocation) {
		this.invoker = invoker;
		this.invocation = invocation;
	}
	
	@Override
	public String getTraceId() {
		return RpcContext.getContext().getAttachment(CoreConstants.LIANJIA_TRACEID);
	}

	@Override
	public String getSpanName() {
		return spanNameProvider.resolveSpanName(RpcContext.getContext());
	}
	
	@Override
	public boolean isSampled() {
		return "1".equals(RpcContext.getContext().getAttachment(CoreConstants.LIANJIA_SAMPLED)) ? true : false;
	}

	@Override
	public void addSpanIdToRequest(SpanId spanId) {
		String application = RpcContext.getContext().getUrl().getParameter("application");
		RpcContext.getContext().setAttachment("clientName", application);
		if (spanId == null) {
			RpcContext.getContext().setAttachment("sampled", "0");
		} else {
			RpcContext.getContext().setAttachment("traceId", spanId.traceId);
			RpcContext.getContext().setAttachment("spanId", spanId.spanId);
			if (spanId.nullableParentId() != null) {
				RpcContext.getContext().setAttachment("parentId", spanId.parentId);
			}
		}
	}

	@Override
	public Collection<KeyValueAnnotation> requestAnnotations() {
		return Collections.singletonList(KeyValueAnnotation.create("url", RpcContext.getContext().getUrl().toString()));
	}

	@Override
	public Endpoint serverAddress() {
		InetSocketAddress inetSocketAddress = RpcContext.getContext().getRemoteAddress();
		String ip = RpcContext.getContext().getUrl().getIp();
		String serverName = serverNameProvider.resolveServerName(RpcContext.getContext());
		return Endpoint.create(ip, inetSocketAddress.getPort(), serverName);
	}
}

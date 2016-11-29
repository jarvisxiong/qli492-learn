package com.demo.zipkin.dubbo.adapter;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcContext;
import com.demo.zipkin.dubbo.DubboServerNameProvider;
import com.demo.zipkin.dubbo.DubboSpanNameProvider;
import com.demo.zipkin.dubbo.support.DefaultServerNameProvider;
import com.demo.zipkin.dubbo.support.DefaultSpanNameProvider;
import com.demo.zipkin.util.IPConversion;
import com.github.kristofa.brave.ClientRequestAdapter;
import com.github.kristofa.brave.IdConversion;
import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.SpanId;
import com.github.kristofa.brave.internal.Nullable;
import com.twitter.zipkin.gen.Endpoint;

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
	public String getSpanName() {
		// 使用接口名+方法名作为span名称
		return spanNameProvider.resolveSpanName(RpcContext.getContext());
	}

	@Override
	public void addSpanIdToRequest(@Nullable SpanId spanId) {
		String application = RpcContext.getContext().getUrl().getParameter("application");
		RpcContext.getContext().setAttachment("clientName", application);
		if (spanId == null) {
			RpcContext.getContext().setAttachment("sampled", "0");
		} else {
			RpcContext.getContext().setAttachment("traceId", IdConversion.convertToString(spanId.traceId));
			RpcContext.getContext().setAttachment("spanId", IdConversion.convertToString(spanId.spanId));
			if (spanId.nullableParentId() != null) {
				RpcContext.getContext().setAttachment("parentId", IdConversion.convertToString(spanId.parentId));
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
		String ipAddr = RpcContext.getContext().getUrl().getIp();
		String serverName = serverNameProvider.resolveServerName(RpcContext.getContext());
		return Endpoint.create(serverName, IPConversion.convertToInt(ipAddr), inetSocketAddress.getPort());
	}
}

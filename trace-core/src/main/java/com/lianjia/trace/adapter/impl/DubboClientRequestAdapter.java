package com.lianjia.trace.adapter.impl;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcContext;
import com.lianjia.trace.Endpoint;
import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.Span;
import com.lianjia.trace.adapter.ClientRequestAdapter;
import com.lianjia.trace.constants.CoreConstants;

@SuppressWarnings("unused")
public class DubboClientRequestAdapter implements ClientRequestAdapter {
	private Invoker<?> invoker;
	private Invocation invocation;

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
		String path = RpcContext.getContext().getUrl().getPath();
		String simpleName = path.substring(path.lastIndexOf(".") + 1);
		return simpleName + "." + RpcContext.getContext().getMethodName();
	}

	@Override
	public boolean isSampled() {
		return "1".equals(RpcContext.getContext().getAttachment(CoreConstants.LIANJIA_SAMPLED)) ? true : false;
	}

	@Override
	public void addSpanToRequest(Span span) {
		String application = RpcContext.getContext().getUrl().getParameter("application");
		RpcContext.getContext().setAttachment("clientName", application);
		if (span == null) {
			RpcContext.getContext().setAttachment("sampled", "0");
		} else {
			RpcContext.getContext().setAttachment("traceId", span.getTraceId());
			RpcContext.getContext().setAttachment("spanId", span.getId());
			if (!span.isRootSpan()) {
				RpcContext.getContext().setAttachment("parentId", span.getParentId());
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
		String path = RpcContext.getContext().getUrl().getPath();
		String serverName = path.substring(path.lastIndexOf(".") + 1);
		return Endpoint.create(ip, inetSocketAddress.getPort(), serverName);
	}
}

package com.lianjia.trace.adapter.impl;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcContext;
import com.demo.zipkin.dubbo.support.DefaultClientNameProvider;
import com.demo.zipkin.dubbo.support.DefaultSpanNameProvider;
import com.lianjia.trace.KeyValueAnnotation;
import com.lianjia.trace.SpanId;
import com.lianjia.trace.TraceData;
import com.lianjia.trace.adapter.ServerRequestAdapter;
import com.lianjia.trace.dubbo.DubboClientNameProvider;
import com.lianjia.trace.dubbo.DubboSpanNameProvider;
import com.lianjia.trace.tracer.ServerTracer;

@SuppressWarnings("unused")
public class DubboServerRequestAdapter implements ServerRequestAdapter {
	private Invoker<?> invoker;
	private Invocation invocation;
	private ServerTracer serverTracer;
	private final static DubboSpanNameProvider spanNameProvider = new DefaultSpanNameProvider();
	private final static DubboClientNameProvider clientNameProvider = new DefaultClientNameProvider();

	public DubboServerRequestAdapter(Invoker<?> invoker, Invocation invocation, ServerTracer serverTracer) {
		this.invoker = invoker;
		this.invocation = invocation;
		this.serverTracer = serverTracer;
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
			// 获取spanId
			if (traceId != null && spanId != null) {
				SpanId span = getSpanId(traceId, spanId, parentId);
				return TraceData.create(span, true);
			}
		}
		return TraceData.create();
	}

	@Override
	public String getSpanName() {
		// span名称使用类名称+方法名
		return spanNameProvider.resolveSpanName(RpcContext.getContext());
	}

	@Override
	public Collection<KeyValueAnnotation> requestAnnotations() {
		String ipAddr = RpcContext.getContext().getUrl().getIp();
		InetSocketAddress inetSocketAddress = RpcContext.getContext().getRemoteAddress();
		// 获取到客户端名称
		final String clientName = clientNameProvider.resolveClientName(RpcContext.getContext());
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

	static SpanId getSpanId(String traceId, String spanId, String parentSpanId) {
		return SpanId.create(traceId, spanId, (parentSpanId == null ? null : parentSpanId));
	}

}

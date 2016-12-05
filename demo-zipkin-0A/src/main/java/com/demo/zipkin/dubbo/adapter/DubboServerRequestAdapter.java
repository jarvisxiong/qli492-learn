package com.demo.zipkin.dubbo.adapter;

import static com.github.kristofa.brave.IdConversion.convertToLong;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcContext;
import com.demo.zipkin.dubbo.DubboClientNameProvider;
import com.demo.zipkin.dubbo.DubboSpanNameProvider;
import com.demo.zipkin.dubbo.support.DefaultClientNameProvider;
import com.demo.zipkin.dubbo.support.DefaultSpanNameProvider;
import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.ServerRequestAdapter;
import com.github.kristofa.brave.ServerTracer;
import com.github.kristofa.brave.SpanId;
import com.github.kristofa.brave.TraceData;

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
			return TraceData.builder().sample(false).build();
		} else {
			final String parentId = invocation.getAttachment("parentId");
			final String spanId = invocation.getAttachment("spanId");
			final String traceId = invocation.getAttachment("traceId");
			// 获取spanId
			if (traceId != null && spanId != null) {
				SpanId span = getSpanId(traceId, spanId, parentId);
				return TraceData.builder().sample(true).spanId(span).build();
			}
		}
		return TraceData.builder().build();

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
		return SpanId.builder().traceId(convertToLong(traceId)).spanId(convertToLong(spanId))
				.parentId(parentSpanId == null ? null : convertToLong(parentSpanId)).build();
	}

}

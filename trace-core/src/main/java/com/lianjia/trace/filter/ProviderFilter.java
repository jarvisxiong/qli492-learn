package com.lianjia.trace.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.lianjia.trace.ServerSpanThreadBinder;
import com.lianjia.trace.Trace;
import com.lianjia.trace.adapter.impl.DubboServerRequestAdapter;
import com.lianjia.trace.adapter.impl.DubboServerResponseAdapter;
import com.lianjia.trace.interceptor.ServerRequestInterceptor;
import com.lianjia.trace.interceptor.ServerResponseInterceptor;

@Activate(group = Constants.PROVIDER)
public class ProviderFilter implements Filter {
	private static volatile Trace trace;
	private static volatile ServerRequestInterceptor serverRequestInterceptor;
	private static volatile ServerResponseInterceptor serverResponseInterceptor;
	@SuppressWarnings("unused")
	private static volatile ServerSpanThreadBinder serverSpanThreadBinder;

	public static void setTrace(Trace trace) {
		ProviderFilter.trace = trace;
		ProviderFilter.serverRequestInterceptor = trace.serverRequestInterceptor();
		ProviderFilter.serverResponseInterceptor = trace.serverResponseInterceptor();
		ProviderFilter.serverSpanThreadBinder = trace.serverSpanThreadBinder();
	}

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		serverRequestInterceptor.handle(new DubboServerRequestAdapter(invoker, invocation, trace.serverTracer()));
		Result rpcResult = invoker.invoke(invocation);
		serverResponseInterceptor.handle(new DubboServerResponseAdapter(rpcResult));
		return rpcResult;
	}
}

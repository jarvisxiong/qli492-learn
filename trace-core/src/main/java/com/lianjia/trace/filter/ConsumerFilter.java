package com.lianjia.trace.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.lianjia.trace.ClientSpanThreadBinder;
import com.lianjia.trace.Trace;
import com.lianjia.trace.adapter.impl.DubboClientRequestAdapter;
import com.lianjia.trace.adapter.impl.DubboClientResponseAdapter;
import com.lianjia.trace.interceptor.ClientRequestInterceptor;
import com.lianjia.trace.interceptor.ClientResponseInterceptor;

@Activate(group = Constants.CONSUMER)
public class ConsumerFilter implements Filter {
	@SuppressWarnings("unused")
	private static volatile Trace trace;
	private static volatile ClientRequestInterceptor clientRequestInterceptor;
	private static volatile ClientResponseInterceptor clientResponseInterceptor;
	private static volatile ClientSpanThreadBinder clientSpanThreadBinder;

	public static void setTrace(Trace trace) {
		ConsumerFilter.trace = trace;
		ConsumerFilter.clientRequestInterceptor = trace.clientRequestInterceptor();
		ConsumerFilter.clientResponseInterceptor = trace.clientResponseInterceptor();
		ConsumerFilter.clientSpanThreadBinder = trace.clientSpanThreadBinder();
	}

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		clientRequestInterceptor.handle(new DubboClientRequestAdapter(invoker, invocation));
		try {
			Result rpcResult = invoker.invoke(invocation);
			clientResponseInterceptor.handle(new DubboClientResponseAdapter(rpcResult));
			return rpcResult;
		} catch (Exception ex) {
			clientResponseInterceptor.handle(new DubboClientResponseAdapter(ex));
			throw ex;
		} finally {
			clientSpanThreadBinder.setCurrentSpan(null);
		}
	}
}

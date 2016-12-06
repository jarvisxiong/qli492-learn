package com.lianjia.trace.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.RpcContext;
import com.lianjia.common.log.RequestIdHolder;
import com.lianjia.trace.constants.CoreConstants;

@Aspect
@Component
public class DubboConsumerAspect implements Ordered {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String EXECUTION = "this(com.alibaba.dubbo.rpc.service.EchoService)";

	public DubboConsumerAspect() {
	}

	@Around(EXECUTION)
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		String traceId = RequestIdHolder.get();
		try {
			RpcContext.getContext().setAttachment(CoreConstants.LIANJIA_TRACEID, traceId);
			Object result = pjp.proceed();
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
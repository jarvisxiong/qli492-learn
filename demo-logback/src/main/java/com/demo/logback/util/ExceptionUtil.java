package com.demo.logback.util;

import com.lianjia.common.exception.BusinessException;
import com.spiderworts.exception.n1.customer.BusinessNoStackTraceException;

public class ExceptionUtil {

	public static void getBusinessException() throws Exception {
		throw new BusinessException(100, "抛出BusinessException异常", new NullPointerException());
	}
	
	public static void getBusinessNoStackTraceException() throws Exception {
		throw new BusinessNoStackTraceException(200, "抛出BusinessNoStackTraceException异常");
	}
}
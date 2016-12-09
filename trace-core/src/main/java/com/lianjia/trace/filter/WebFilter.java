package com.lianjia.trace.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lianjia.trace.util.TraceIdAndNodePathHolder;
import com.lianjia.trace.util.TraceIdGenerator;

public class WebFilter implements Filter {
	 private Logger logger = LoggerFactory.getLogger(getClass());
	 private String serviceName;

	public void init(FilterConfig config) throws ServletException {
		serviceName = config.getInitParameter("serviceName");
		if (serviceName == null) {
			serviceName = "unknown";
		}
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		String traceId = TraceIdGenerator.getTraceId(TraceIdGenerator.TYPE_HTTP);
		TraceIdAndNodePathHolder.setTraceIdAndNodePath(traceId, serviceName);
		logger.info("--------------------" + traceId);
		chain.doFilter(req, res);
		
		TraceIdAndNodePathHolder.clear();
	}
}

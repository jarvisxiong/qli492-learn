package com.lianjia.trace.util;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LogTraceIdConvert extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		String traceId = TraceIdAndNodePathHolder.getTraceId();
		if (traceId != null) {
			String logTraceId = traceId + "-" + TraceIdAndNodePathHolder.getNodePath();
			return logTraceId;
		}
		return "";
	}
}

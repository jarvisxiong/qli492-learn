package com.lianjia.trace.util;

import com.alibaba.ttl.TransmittableThreadLocal;

public class TraceIdAndNodePathHolder {
	private static TransmittableThreadLocal<String> transferTraceId = new TransmittableThreadLocal<String>();
	private static TransmittableThreadLocal<String> transferNodePth = new TransmittableThreadLocal<String>();

	public static String getTraceId() {
		return transferTraceId.get();
	}
	
	public static String getNodePath() {
		return transferNodePth.get();
	}

	public static void setTraceIdAndNodePath(String traceId, String nodePath) {
		TraceIdAndNodePathHolder.transferTraceId.set(traceId);
		TraceIdAndNodePathHolder.transferNodePth.set(nodePath);
		MDCHolder.initTime();
	}

	public static void clear() {
		TraceIdAndNodePathHolder.transferTraceId.remove();
		TraceIdAndNodePathHolder.transferNodePth.remove();
		MDCHolder.clear();
	}
}

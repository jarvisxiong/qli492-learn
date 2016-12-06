package com.lianjia.common.log;

public class RequestIdHolder {
	private static ThreadLocal<String> instance = new ThreadLocal<String>();

	public static String get() {
		return instance.get();
	}

	public static void set(String reqId) {
		RequestIdHolder.instance.set(reqId);
		MDCHolder.initTime();
	}
}

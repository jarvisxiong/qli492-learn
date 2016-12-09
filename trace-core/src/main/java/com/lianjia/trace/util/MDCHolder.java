package com.lianjia.trace.util;

public class MDCHolder {

	private static final ThreadLocal<Long> time = new ThreadLocal<Long>();
	
	private static final ThreadLocal<Long> lastTime = new ThreadLocal<Long>();

	public static Long getTime() {
		return time.get();
	}

	public static void initTime() {
		time.set(System.currentTimeMillis());
		lastTime.set(System.currentTimeMillis());
	}
	
	public static Long getLastTime() {
		return lastTime.get();
	}
	
	public static void updateLastTime() {
		lastTime.set(System.currentTimeMillis());
	}

	public static void clear() {
		time.remove();
		lastTime.remove();
	}
}

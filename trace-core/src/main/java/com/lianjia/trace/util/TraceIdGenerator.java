package com.lianjia.trace.util;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import com.lianjia.trace.LocalIpAddressUtil;

public class TraceIdGenerator {
	public final static String TYPE_HTTP = "Http";
	public final static String TYPE_DUBBO = "Dubbo";
	public final static String TYPE_API = "Api";

	// ReqId格式：来源(Dubbo/Http/Api...)-机器IP-当前时分秒-线程随机数
	private final static String SOURCE_LOCIP_TIME_THREAD = "%s-%s-%s-%s";

	private final static String LOCAL_IP = LocalIpAddressUtil.resolveLocalAddress().getHostAddress();

	private static AtomicLong threadId = new AtomicLong();

	private static String atSecond = DateUtil.getFormatDate(new Date(), "yyyyMMddHHmmss");

	public static String getTraceId(String type) {
		String time = DateUtil.getFormatDate(new Date(), "yyyyMMddHHmmss");
		if (!time.equals(atSecond)) {
			threadId.set(0);
			atSecond = time;
		}
		return String.format(SOURCE_LOCIP_TIME_THREAD, type, LOCAL_IP, time, threadId.incrementAndGet());
	}

	public static void main(String[] args) throws InterruptedException {
		String a = getTraceId(TraceIdGenerator.TYPE_HTTP);
		System.out.println(a);
	}
}

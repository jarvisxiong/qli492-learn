package com.lianjia.trace.util;

import java.util.HashMap;
import java.util.Map;

public class SpanNumHolder {
	private static final ThreadLocal<Map<String, Integer>> AUTO_NUM = new ThreadLocal<Map<String, Integer>>();
	
	public static Integer plusNum(String traceId) {
		if (traceId == null) {
			return 0;
		}
		Integer num = null;
		if (AUTO_NUM.get() == null) {
			AUTO_NUM.set(new HashMap<String, Integer>());
		}
		num = AUTO_NUM.get().get(traceId);
		if (num == null) {
			num = 1;
			AUTO_NUM.get().put(traceId, num);
		} else {
			num = num + 1;
			AUTO_NUM.get().put(traceId, num);
		}
		return num;
	}

	public static Integer getNum(String traceId) {
		if (traceId == null) {
			return 0;
		}
		if (AUTO_NUM.get() == null) {
			return 0;
		}
		Integer num = AUTO_NUM.get().get(traceId);
		if (num == null) {
			num = 0;
		}
		return num;
	}
}
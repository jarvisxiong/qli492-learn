package com.lianjia.trace.layout;

import com.lianjia.trace.util.LogTraceIdConvert;

import ch.qos.logback.classic.PatternLayout;

public class TraceIdPatternLayout extends PatternLayout {
	public static final String TRACEID = "traceId";
	private static final String DEFAULT = "%date{yyyy-MM-dd HH:mm:ss.SSS} %" + TRACEID
			+ " %-5level %logger{36}.%M - %msg%n";
	private String pattern;

	static {
		defaultConverterMap.put(TRACEID, LogTraceIdConvert.class.getName());
	}

	public TraceIdPatternLayout() {
		super();
		setPattern(this.getPattern());
		if (this.getPattern() == null) {
			super.setPattern(DEFAULT);
		}
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}

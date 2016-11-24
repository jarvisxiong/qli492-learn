package com.demo.logback.util;

import com.lianjia.common.log.RlaLogCode;

public enum CustomizedRlaLogCode implements RlaLogCode{
	
	debug(100001, ""),
	debugInternalServer(100002, "Internal server debug"),
	debugRelyServer(100003, "Rely on external system debug"),

	info(200001, ""),
	infoInternalServer(200002, "Internal server info"),
	infoRelyServer(200003, "Rely on external system info"),
	infoWithParam(200004, "InfoWithParam value={}"),

	warn(300001, ""),
	warnInternalServer(300002, "Internal server warn"),
	warnRelyServer(300003, "Rely on external system warn"),

	error(400001, ""),
	errorInternalServer(400002, "Internal server error"),
	
	trace(500001, ""),
	traceFilterIgnoreUrl(500101, "Ignoring URI {}"),
	;
	private Integer code;
	private String format;

	private CustomizedRlaLogCode(Integer code, String format) {
		this.code = code;
		this.format = format;
	}

	public Integer getCode() {
		return code;
	}

	public String getFormat() {
		return format;
	}
}

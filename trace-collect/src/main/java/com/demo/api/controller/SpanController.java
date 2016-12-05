package com.demo.api.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lianjia.trace.Span;
import com.lianjia.trace.util.JsonUtil;

@RestController
public class SpanController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping(value = "/api/span", method = RequestMethod.POST)
	public String span(byte[] json) throws InterruptedException, IOException {
		TypeReference<List<Span>> type = new TypeReference<List<Span>>() {};
		List<Span> spans = JsonUtil.byte2Object(json, type);
		String s = JSON.toJSONString(spans, true);
		System.out.println(s);
		logger.info(s);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/test", method = RequestMethod.GET)
	public String test() throws InterruptedException, IOException {
		return "success";
	}
}
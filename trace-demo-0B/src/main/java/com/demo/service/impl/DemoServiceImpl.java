package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.service.DemoService0B;
import com.demo.service.DemoService0C;

public class DemoServiceImpl implements DemoService0B {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	@SuppressWarnings("resource")
	public String method0B() {
		String result = "";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml");
		DemoService0C b0 = context.getBean("demoService0C", DemoService0C.class);
		result = b0.method0C();
		logger.info("method0B." + result);
		return "method0B." + result;
	}
}

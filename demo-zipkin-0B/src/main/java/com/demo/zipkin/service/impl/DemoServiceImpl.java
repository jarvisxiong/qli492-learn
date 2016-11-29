package com.demo.zipkin.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.zipkin.service.DemoService0B;
import com.demo.zipkin.service.DemoService0C;

@SuppressWarnings("resource")
public class DemoServiceImpl implements DemoService0B {

	@Override
	public String method0B() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml", "applicationContext-service-consumer.xml");
		DemoService0C obj = context.getBean("demoService0C", DemoService0C.class);
		String result = obj.method0C();
		return "method0B-" + result;
	}
}

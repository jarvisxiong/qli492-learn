package com.demo.zipkin.dubbo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.zipkin.service.DemoService;

public class SpringConsumer {
	protected static Logger logger = LoggerFactory.getLogger(SpringConsumer.class.getName());
	private static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml", "applicationContext-service.xml");

	public static void main(String[] args) {
		DemoService obj = context.getBean("demoService", DemoService.class);
		String result = obj.first();
		System.out.println(result);
	}
}

package com.demo.zipkin.dubbo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.zipkin.service.DemoService0B;

public class SpringConsumer {
	protected static Logger logger = LoggerFactory.getLogger(SpringConsumer.class.getName());
	private static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml", "applicationContext-service-consumer.xml");

	public static void main(String[] args) {
		DemoService0B obj = context.getBean("demoService0B", DemoService0B.class);
		String result = obj.method0B();
		System.out.println(result);
	}
}

package com.demo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.service.DemoService0B;
import com.demo.service.DemoService0C;

public class SpringConsumer {
	protected static Logger logger = LoggerFactory.getLogger(SpringConsumer.class.getName());
	private static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml", "applicationContext-service-consumer.xml");

	public static void main(String[] args) {
		String result = "";
		DemoService0B b0 = context.getBean("demoService0B", DemoService0B.class);
		result = b0.method0B();
		
		DemoService0C d0 = context.getBean("demoService0C", DemoService0C.class);
		result = result + "||" + d0.method0C();
		System.out.println(result);
	}
}

package com.demo.zipkin.dubbo.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("unused")
public class SpringProvider {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-provider.xml", "applicationContext-service.xml");
		while(true) {
			Thread.sleep(10000);
		}
	}
}

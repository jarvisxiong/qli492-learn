package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.service.DemoService0C;

public class DemoServiceImpl implements DemoService0C {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String method0C() {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("applicationContext-consumer.xml");
		// SemainFacade semian = context.getBean("semainFacade",
		// SemainFacade.class);
		// try {
		// semian.getMenuDTO(SystemType.CUSTOMER, "110000");
		// } catch (SemainApiException e) {
		// e.printStackTrace();
		// }
		logger.info("method0C");
		return "method0C";
	}
}

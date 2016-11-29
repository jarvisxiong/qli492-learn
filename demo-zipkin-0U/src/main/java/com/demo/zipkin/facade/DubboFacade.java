package com.demo.zipkin.facade;

import com.demo.zipkin.service.DemoService0A;

public class DubboFacade {

	public static DemoService0A getDemoService0A() {
		return SpringContextUtils.getBean("demoService0A", DemoService0A.class);
	}

}
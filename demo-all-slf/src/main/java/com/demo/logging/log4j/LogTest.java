package com.demo.logging.log4j;

import org.junit.Test;

public class LogTest {

	@Test
	public void testOverLog4j() throws Exception { // 桥接log4j
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
		logger.info("log4j now Hello World");
	}

	@Test
	public void testOverJcl() throws Exception { // 桥接jcl
		org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(LogTest.class);
		logger.info("Hello World");
	}

	@Test
	public void testOverJul() throws Exception { // 桥接jul
		org.slf4j.bridge.SLF4JBridgeHandler.install(); // 关键行，必须要提前注册这个处理器
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LogTest.class.getName());
		logger.info("Hello World");
	}
}
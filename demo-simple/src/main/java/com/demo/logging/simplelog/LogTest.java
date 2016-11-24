package com.demo.logging.simplelog;

import org.junit.Test;

public class LogTest {

	@Test
	public void testSimpleLog() throws Exception {
		org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(LogTest.class.getName());
		log.info("Here is INFO message"); // 由于配置日志级别未能打印
		log.warn("Here is WARN message"); // 由于配置日志级别未能打印
		log.error("Here is ERROR message");
	}
}
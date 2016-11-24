package com.demo.logging.log4j;

import org.junit.Test;

public class LogTest {

	@Test
	public void testJul() throws Exception {
		java.util.logging.Logger logger = com.demo.logging.util.Logging.getLogger(LogTest.class.getName());
		logger.info("Here is INFO message");
		logger.warning("Here is WARN message");
		// 没有debug/error级别日志
		logger.log(java.util.logging.Level.OFF, "Here is OFF message");
	}

	@Test
	public void testLog4j1() throws Exception {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LogTest.class);
		logger.debug("Here is DEBUG messgae");
		logger.info("Here is INFO message");
		logger.warn("Here is WARN message");
		logger.error("Here is ERROR message");
		logger.fatal("Here is FATAL message");
	}
	
	@Test
	public void testLog4j2() throws Exception {
		org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
		logger.debug("Here is DEBUG messgae");
		logger.info("Here is INFO message");
		logger.warn("Here is WARN message");
		logger.error("Here is ERROR message");
		logger.fatal("Here is FATAL message");
	}
}
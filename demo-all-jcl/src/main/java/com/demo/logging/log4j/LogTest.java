package com.demo.logging.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class LogTest {

	@Test
	public void testSimpleLog() throws Exception {
		// 如果没有log4j，commons-logging会将相应的输出转化成JDK Logging的输出，不能输出debug级别
		// 只要我们在项目中添加了log4j的jar包，那么commons-logging就会自动切到log4j的日志输出。
		// 不提供log4j.properties配置文件，按照默认输出,log4j不支持占位符打印
		Log logger = LogFactory.getLog(LogTest.class);
		logger.debug("debug()...");
		logger.info("info()...");
		logger.error("error()...");
	}
}
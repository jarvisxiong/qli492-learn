package com.demo.logging;

import org.junit.Test;

import com.demo.logging.test.jul.JulPojoTest;
import com.demo.logging.test.jul.JulSimpleTest;
import com.demo.logging.test.log4j.Log4jPojoTest;
import com.demo.logging.test.log4j.Log4jSimpleTest;
import com.demo.logging.test.log4j2.Log4j2PojoTest;
import com.demo.logging.test.log4j2.Log4j2SimpleTest;
import com.demo.logging.test.logback.LogbackPojoTest;
import com.demo.logging.test.logback.LogbackSimpleTest;
import com.demo.logging.util.LogPrint;

public class LogTest {

	@Test
	public void testSimpleLog() throws Exception {
		LogPrint.print("simple-jul-test.log", new JulSimpleTest());
		LogPrint.print("simple-log4j-test.log", new Log4jSimpleTest());
		LogPrint.print("simple-log4j2-test.log", new Log4j2SimpleTest());
		// 需注释掉log4j-slf4j-impl包避免冲突
		LogPrint.print("simple-logback-test.log", new LogbackSimpleTest());
	}

	@Test
	public void testPojoLog() throws Exception {
		LogPrint.print("pojo-jul-test.log", new JulPojoTest());
		LogPrint.print("pojo-log4j-test.log", new Log4jPojoTest());
		LogPrint.print("pojo-log4j2-test.log", new Log4j2PojoTest());
		// 需注释掉log4j-slf4j-impl包避免冲突
		LogPrint.print("pojo-logback-test.log", new LogbackPojoTest());
	}

	@Test
	public void testLog() throws Exception {
		// LogPrint.print("pojo-jul-test.log", new JulPojoTest());
		// LogPrint.print("pojo-log4j-test.log", new Log4jPojoTest());
		// LogPrint.print("pojo-log4j2-test.log", new Log4j2PojoTest());
		 LogPrint.print("pojo-logback-test.log", new LogbackPojoTest());
	}
}
package com.demo.logging.log4j;

import org.junit.Test;

public class LogTest {

	@Test
	public void testLog4j1() throws Exception {
		//	SLF4J: Class path contains multiple SLF4J bindings.
		//	SLF4J: Found binding in [jar:file:/C:/Users/Administrator/.m2/repository/org/slf4j/slf4j-log4j12/1.7.2/slf4j-log4j12-1.7.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
		//	SLF4J: Found binding in [jar:file:/C:/Users/Administrator/.m2/repository/org/slf4j/slf4j-jdk14/1.7.5/slf4j-jdk14-1.7.5.jar!/org/slf4j/impl/StaticLoggerBinder.class]
		//	SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
		//	SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]
		//	[main] INFO  com.demo.logging.log4j.LogTest - now starting server
		
		// 	slf4j-log4j12 完成API转换,将对log4j的调用转为slf4j调用
		//	log4j-over-slf4j完成API转化，将对slf4j的调用转为log4j调用
		
		// slf4j-log4j12 与 log4j-over-slf4j不能同时存在，即 A->B/B->A
		// log4j在log4j-over-slf4j之前，能够正常输出日志
		// 调整顺序，出现stack overflow异常
		org.slf4j.Logger log1 = org.slf4j.LoggerFactory.getLogger(LogTest.class);  
		log1.info("slf4j now {}", "Hello World"); 
		
		// org.apache.log4j.Logger log2 = org.apache.log4j.Logger.getLogger(LogTest.class);
		// log2.info("log4j now Hello World");
		
		// jul日志输出
		// java.util.logging.Logger log3 = java.util.logging.Logger.getLogger(LogTest.class.getName());
		// log3.info("jul now Hello World");
	}
	
	@Test
	public void testLog4j2() throws Exception {
		// 与上面log4j1使用的是同样的API接口，编译时不清楚绑定log4j1还是log4j2
		org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LogTest.class);
		logger.info("now {}", "Hello World"); 
	}
	
	@Test
	public void testJul() throws Exception {
		org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LogTest.class);
		logger.info("now {}", "Hello World"); 
	}
}
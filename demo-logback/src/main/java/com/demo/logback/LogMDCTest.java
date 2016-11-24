package com.demo.logback;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.spiderworts.util.n1.file.FileUtil;

public class LogMDCTest {
	String path = LogMDCTest.class.getResource("/").getPath();
	
	@Before
	public void before() {
		FileUtil.move(new File(path + "logback.xml"), new File(path + "tmp.xml"));
		FileUtil.copy(new File(path + "logback-mdc.xml"), new File(path + "logback.xml"));
		System.out.println(path);
	}
	
	@Test
	public void testMdcLog() throws Exception {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		MDC.put("CUSTOMER_ID", "501000000001");
		MDC.put("THREAD_ID", "Thread" + String.valueOf(Thread.currentThread().getId()));
		logger.info("MDC上下文,info级别日志");
	}
}
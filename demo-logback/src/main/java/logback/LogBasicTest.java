package logback;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.logback.LogMDCTest;
import com.spiderworts.util.n1.file.FileUtil;

public class LogBasicTest {
	String path = LogMDCTest.class.getResource("/").getPath();

	@Test
	public void testLog1() throws Exception {
		// 只配置root
		FileUtil.copy(new File(path + "logback-basic-1.xml"), new File(path + "logback.xml"));
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}
	
	@Test
	public void testLog2() throws Exception {
		// 带有loger的配置，不指定级别，不指定appender
		FileUtil.copy(new File(path + "logback-basic-2.xml"), new File(path + "logback.xml"));
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}
	
	@Test
	public void testLog3() throws Exception {
		// 带有多个loger的配置，指定级别，指定appender
		FileUtil.copy(new File(path + "logback-basic-3.xml"), new File(path + "logback.xml"));
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}
	
	@Test
	public void testLog4() throws Exception {
		// 带有多个loger的配置，指定级别，指定appender
		FileUtil.copy(new File(path + "logback-basic-4.xml"), new File(path + "logback.xml"));
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}
}

package com.demo.logback;

import java.io.File;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.logback.util.CustomizedRlaLogCode;
import com.demo.logback.util.ExceptionUtil;
import com.lianjia.common.log.RequestIdHolder;
import com.lianjia.common.log.RequestIdUtil;
import com.lianjia.common.log.RlaLogUtil;
import com.spiderworts.util.n1.file.FileUtil;

@SuppressWarnings("unused")
public class LogCustTest {
	String path = LogMDCTest.class.getResource("/").getPath();

	@Before
	public void before() {
		FileUtil.copy(new File(path + "logback-cust.xml"), new File(path + "logback.xml"));
	}

	@Test
	public void testLog() throws Exception {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		RequestIdHolder.set(RequestIdUtil.getReqId(RequestIdUtil.HTTP));
		logger.info("logback now {}", new Date());
		RlaLogUtil.log(logger, CustomizedRlaLogCode.infoInternalServer, "value=1");
		RlaLogUtil.log(logger, CustomizedRlaLogCode.infoWithParam, "1");
		RlaLogUtil.log(logger, CustomizedRlaLogCode.warnInternalServer, "value=1");

		int divisor = 0;
		try {
			int i = 1 / divisor;
		} catch (Exception e) {
			RlaLogUtil.log(logger, e, CustomizedRlaLogCode.errorInternalServer);
		}

		try {
			int i = 10 / divisor;
		} catch (Exception e) {
			RlaLogUtil.log(logger, e, CustomizedRlaLogCode.errorInternalServer, divisor);
		}

		try {
			ExceptionUtil.getBusinessException();
		} catch (Exception e) {
			RlaLogUtil.log(logger, e, CustomizedRlaLogCode.errorInternalServer, divisor);
		}

		try {
			ExceptionUtil.getBusinessNoStackTraceException();
		} catch (Exception e) {
			RlaLogUtil.log(logger, e, CustomizedRlaLogCode.errorInternalServer, divisor);
		}
	}
	
	@Test
	public void testLog2() throws Exception {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("logback now {}", new Date());
	}
}
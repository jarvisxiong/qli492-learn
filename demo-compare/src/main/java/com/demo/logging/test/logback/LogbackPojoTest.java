package com.demo.logging.test.logback;

import com.demo.logging.pojo.User;
import com.demo.logging.test.base.LogbackTest;

public class LogbackPojoTest extends LogbackTest {
	@Override
	protected void test() {
		logger.info("to test: [POJO] [" + new User() + "]");
		//logger.info("to test: [POJO] [{}]", new User());
	}
}

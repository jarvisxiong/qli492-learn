package com.demo.logging.test.log4j;

import com.demo.logging.pojo.User;
import com.demo.logging.test.base.Log4jTest;

public class Log4jPojoTest extends Log4jTest {
	@Override
	protected void test() {
		logger.info("to test: [POJO] [" + new User() + "]");
	}
}

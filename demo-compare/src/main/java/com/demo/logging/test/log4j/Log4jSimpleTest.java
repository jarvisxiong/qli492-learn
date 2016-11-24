package com.demo.logging.test.log4j;

import com.demo.logging.test.base.Log4jTest;

public class Log4jSimpleTest extends Log4jTest {
	@Override
	protected void test() {
		logger.info("simple test");
	}
}

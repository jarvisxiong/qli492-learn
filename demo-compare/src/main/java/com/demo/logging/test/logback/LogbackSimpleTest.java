package com.demo.logging.test.logback;

import com.demo.logging.test.base.LogbackTest;

public class LogbackSimpleTest extends LogbackTest {
	@Override
	protected void test() {
		logger.info("simple test");
	}
}

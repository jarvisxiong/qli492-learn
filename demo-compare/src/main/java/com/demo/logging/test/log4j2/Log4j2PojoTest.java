package com.demo.logging.test.log4j2;

import com.demo.logging.pojo.User;
import com.demo.logging.test.base.Log4j2Test;

public class Log4j2PojoTest extends Log4j2Test {
	@Override
	protected void test() {
		logger.info("to test: [POJO] [" + new User() + "]");
	}
}

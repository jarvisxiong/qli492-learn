package com.demo.logging.test.jul;

import com.demo.logging.pojo.User;
import com.demo.logging.test.base.JulTest;

public class JulPojoTest extends JulTest {
	@Override
	protected void test() {
		logger.info("to test: [POJO] [" + new User() + "]");
	}
}

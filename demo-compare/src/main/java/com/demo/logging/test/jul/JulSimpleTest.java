package com.demo.logging.test.jul;

import com.demo.logging.test.base.JulTest;

public class JulSimpleTest extends JulTest {
	@Override
	protected void test() {
		logger.info("simple test");
	}
}

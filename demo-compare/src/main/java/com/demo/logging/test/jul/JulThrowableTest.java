package com.demo.logging.test.jul;

import java.util.logging.Level;

import com.demo.logging.test.base.JulTest;

public class JulThrowableTest extends JulTest {
	@Override
	protected void test() {
		logger.log(Level.INFO, "to test: [Throwable] [{}]", new IllegalStateException("throwable test"));
	}
}

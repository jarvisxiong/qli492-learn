package com.demo.logging.test.base;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LogbackTest implements Runnable {
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			test();
			doSomethingBetweenLogging();
		}
	}

	protected abstract void test();

	private void doSomethingBetweenLogging() {
		Random rand = new Random();
		int num = rand.nextInt(200);
		isPrime(num);
	}

	private boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}

package com.demo.logging.test.base;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Log4j2Test implements Runnable {
	public Logger logger = LogManager.getLogger(this.getClass());

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

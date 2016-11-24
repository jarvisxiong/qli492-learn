package com.demo.logging.util;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.spiderworts.util.n1.file.FileUtil;

public class LogPrint {
	private final static String LOG_PATH = "/home/work/var/log/demo-logging/";
	private final static String LOG_FILE = "test.log";

	public static void print(String fileName, Runnable instance) throws Exception {
		FileUtil.clearFileContent(LOG_PATH + LOG_FILE);
		FileUtil.clearFileContent(LOG_PATH + fileName);
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		for (int j = 0; j < 10; j++) {
			threadPool.execute(instance);
		}
		threadPool.awaitTermination(1 * 1000, TimeUnit.MILLISECONDS);
		threadPool.shutdownNow();
		FileUtil.copy(new File(LOG_PATH + LOG_FILE), new File(LOG_PATH + fileName));
		FileUtil.clearFileContent(LOG_PATH + LOG_FILE);
	}
}
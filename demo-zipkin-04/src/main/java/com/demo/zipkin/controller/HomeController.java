package com.demo.zipkin.controller;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("second2four")
	public String start() throws InterruptedException, IOException {
		Random random = new Random();
		int sleep = random.nextInt(1000);
		TimeUnit.MILLISECONDS.sleep(sleep);
		return " [service4 sleep " + sleep + " ms]";
	}
}
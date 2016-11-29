package com.demo.zipkin.controller;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.zipkin.facade.DubboFacade;
import com.demo.zipkin.service.DemoService0A;

@RestController
public class HomeController {

	private Random random = new Random();

	@RequestMapping("demo")
	public String start() throws InterruptedException, IOException {
		int sleep = random.nextInt(100);
		TimeUnit.MILLISECONDS.sleep(sleep);
		DemoService0A demo = DubboFacade.getDemoService0A();
		return " [service1 sleep " + sleep + " ms]" + demo.method0A();
	}
}
package com.demo.zipkin.controller;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class HomeController {
	@Autowired
	private OkHttpClient client;

	@RequestMapping("first2second")
	public String start() throws InterruptedException, IOException {
		Random random = new Random();
		int sleep = random.nextInt(100);
		TimeUnit.MILLISECONDS.sleep(sleep);
		Request request = new Request.Builder().url("http://127.0.0.1:9091/second2three").get().build(); // service3
		Response response = client.newCall(request).execute();
		String result = response.body().string();
		request = new Request.Builder().url("http://127.0.0.1:9092/second2four").get().build(); // service4
		response = client.newCall(request).execute();
		result += response.body().string();
		return " [service2 sleep " + sleep + " ms]" + result;
	}
}
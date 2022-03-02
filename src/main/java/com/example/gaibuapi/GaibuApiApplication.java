package com.example.gaibuapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class GaibuApiApplication {
	private TwitterThread twitterThread = null;

	public static void main(String[] args) {

		try (
				ConfigurableApplicationContext ctx = SpringApplication.run(GaibuApiApplication.class, args)) {
			GaibuApiApplication app = ctx.getBean(GaibuApiApplication.class);
			app.run();
		} catch (
				Exception e) {
			System.out.println("Application Error!");
			e.printStackTrace();
		}
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


	private void run() throws Exception {
		System.out.println("Application Run!");
		twitterThread = new TwitterThread();
		twitterThread.start();
		while (true) {
			Thread.sleep(10 * 1000L);
		}
	}

	@PreDestroy
	public void exit() {
		if (twitterThread != null) {
			twitterThread.stopThread();
		}
		System.out.println("Application exit!");
	}

}

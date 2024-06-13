package com.practice.java21virtualthread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class Java21VirtualThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java21VirtualThreadApplication.class, args);
	}

}

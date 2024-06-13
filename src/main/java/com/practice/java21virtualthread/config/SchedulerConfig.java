package com.practice.java21virtualthread.config;

import org.springframework.boot.task.SimpleAsyncTaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SchedulerConfig {
	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		//Platform Thread Pool 을 사용하는 ThreadPoolTaskScheduler 를 생성
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setThreadNamePrefix("myScheduler-");
		threadPoolTaskScheduler.setPoolSize(10);
		// TODO: more settings...
		return threadPoolTaskScheduler;
	}

	@Primary
	@Bean
	public SimpleAsyncTaskScheduler simpleAsyncTaskScheduler(SimpleAsyncTaskSchedulerBuilder builder) {
		//SimpleAsyncTaskSchedulerBuilder 는 "Virtual Thread" 를 사용하는 SimpleAsyncTaskScheduler 를 생성하는 빌더 클래스
		return builder.build();
	}
}

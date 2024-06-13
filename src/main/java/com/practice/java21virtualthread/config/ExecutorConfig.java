package com.practice.java21virtualthread.config;

import org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ExecutorConfig {
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		// Platform Thread Pool 을 사용하는 ThreadPoolTaskExecutor 를 생성
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(10);
		threadPoolTaskExecutor.setThreadNamePrefix("myThreadPool-");
		// TODO: more setting...
		return threadPoolTaskExecutor;
	}

	/**
	 * taskExecutor의 이름을 가진 Bean이 우선순위가 제일 높다. (Primary 보다 높음)
	 * -> Controller는 기본을 사용하기 때문에 Virtual Thread를 사용하는 SimpleAsyncTaskExecutor를 사용하게 된다.
	 *
	 * @@Async는 따로 지정을 해줬기 때문에 ThreadPoolTaskExecutor를 사용하게 된다. (Platform Thread Pool)
	 */
	@Bean
	public SimpleAsyncTaskExecutor taskExecutor(SimpleAsyncTaskExecutorBuilder builder) {
		// SimpleAsyncTaskExecutorBuilder 는 "Virtual Thread" 를 사용하는 SimpleAsyncTaskExecutor 를 생성하는 빌더 클래스
		return builder.build();
	}
}

package com.practice.java21virtualthread.purejava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VirtualThreadExecutorsCreation {
	private static final Runnable runnable = new Runnable() {
		@Override
		public void run() {
			log.info("1) run. thread: " + Thread.currentThread());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			log.info("2) run. thread: " + Thread.currentThread());
		}
	};

	public static void main(String[] args) {
		log.info("1) main. thread: " + Thread.currentThread());

		// originThreadExample();
		virtualThreadExample();

		log.info("2) main. thread: " + Thread.currentThread());
	}

	private static void originThreadExample() {
		//best practice
		try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
			for (int i = 0; i < 10; i++) {
				executorService.execute(runnable);
			}
		}
	}

	private static void virtualThreadExample() {
		// newVirtualThreadPerTaskExecutor는 기본 ThreadName을 제공하므로, ThreadFactory를 사용하여 ThreadName을 지정할 수 있음 (아래 예제 코드 참고)
		// try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
		// 	for (int i = 0; i < 100; i++) {
		// 		executorService.execute(runnable);
		// 	}
		// }

		// best practice
		final ThreadFactory factory = Thread.ofVirtual().name("myVirtual-", 0).factory();
		try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory)) {
			for (int i = 0; i < 100; i++) {
				executorService.execute(runnable);
			}
		}

		// anti pattern
		// antiPattern1();
	}

	private static void antiPattern1() {
		ThreadFactory factory = Thread.ofVirtual().name("myVirtual-", 0).factory();
		// virtual thread를 1개만 사용하는 케이스이며, 계속해서 재사용을 하고 있기 때문에 해당 코드는 안티 패턴임 (VirtualThread의 ThreadPool을 사용하는 것은 권장되지 않음)
		try (ExecutorService executorService = Executors.newFixedThreadPool(1, factory)) {
			for (int i = 0; i < 10; i++) {
				executorService.submit(runnable);
			}
		}
	}

	/**
	 * antiPattern1과 동일한 이유로 안티 패턴임 (여기서도 1개의 thread만 사용하고 있음)
	 */
	private static void antiPattern2() {
		ThreadFactory factory = Thread.ofVirtual().name("myVirtual-", 0).factory();
		try (ExecutorService executorService = Executors.newSingleThreadExecutor(factory)) {
			for (int i = 0; i < 10; i++) {
				executorService.submit(runnable);
			}
		}
	}
}

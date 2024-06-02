package com.practice.java21virtualthread.purejava;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Live {
	public static void main(String[] args) throws InterruptedException {
		// Thread thread = new Thread(runnable);
		// thread.start();

		// Thread thread = new Thread(runnable);
		// thread.setDaemon(true);
		// thread.start();
		// thread.join(); // join을 해야지, main thread가 해당 thread가 끝날 때까지 기다림 (daemon thread이기 때문에)
		// Java에서 일반 thread가 없이 daemon thread만 남아있으면 JVM은 종료됨

		// ===========================================

		final Thread thread = Thread.ofVirtual().name("myVirtual").start(runnable); // forkJoinPool에서 실행됨 그리고 forkJoinPool은 daemon thread (즉, VirtualThread는 daemon thread)
		// thread.join(); // join을 해야지, main thread가 해당 thread가 끝날 때까지 기다림
	}

	private static final Runnable runnable = () -> {
		log.info("1) run. thread: {}, class: {}", Thread.currentThread(), Thread.currentThread().getClass());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		log.info("2) run. thread: {}", Thread.currentThread());
	};
}

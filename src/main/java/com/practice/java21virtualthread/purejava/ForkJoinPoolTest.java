package com.practice.java21virtualthread.purejava;

import java.util.List;
import java.util.Optional;

public class ForkJoinPoolTest {
	public static void main(String[] args) {
		List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		final Optional<Integer> op = list.parallelStream()
			.filter(i -> {
				System.out.println("i: " + i + ", thread: " + Thread.currentThread() + ", isDaemon: " + Thread.currentThread().isDaemon() + ", class: " + Thread.currentThread().getClass());
				return i % 2 == 0;
			})
			.findAny();

		System.out.println(op.get());

		// Java에서 ThreadPool이 필요한데, 사용자가 직접 할당한게 없다면 ForkJoinPool을 사용함 (ForkJoinPool은 daemon thread)
	}
}

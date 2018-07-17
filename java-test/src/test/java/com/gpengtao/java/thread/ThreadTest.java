package com.gpengtao.java.thread;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author pengtao.geng on 2018/7/17 下午6:39.
 */
public class ThreadTest {

	@Test
	public void test_throw_exception() {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 1L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1000));

		for (int i = 0; i < 10; i++) {
			pool.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread());
					throw new RuntimeException("xxx");
				}
			});
		}

		System.out.println("main end");
	}
}

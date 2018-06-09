package com.gpengtao.java.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by pengtao.geng on 2018/5/25 下午5:03.
 */
public class LockTest {

    @Test
    public void test1() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();

        lock.tryLock();

        lock.tryLock(1, TimeUnit.SECONDS);
    }

    @Test
    public void test_thread_lock() throws InterruptedException {
        // 主线程lock
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " lock");

        Thread thread = new Thread(() -> {
            // 子线程lock
            System.out.println(Thread.currentThread().getName() + " running");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " finished");
        });
        thread.start();

        TimeUnit.SECONDS.sleep(2000000);

        // 主线程释放lock
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " unlock");
    }

}

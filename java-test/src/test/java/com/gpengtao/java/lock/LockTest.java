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

}

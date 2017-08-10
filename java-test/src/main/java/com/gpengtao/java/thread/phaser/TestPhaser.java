package com.gpengtao.java.thread.phaser;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Phaser;

/**
 * Created by pengtao.geng on 2017/8/9.
 */
public class TestPhaser {

    private static void runTasks(List<Runnable> tasks) {
        final Phaser phaser = new Phaser(1); // "1" to register self
        // create and start threads
        for (final Runnable task : tasks) {
            phaser.register();
            new Thread() {
                public void run() {
                    phaser.arriveAndAwaitAdvance(); // await all creation
                    task.run();
                }
            }.start();
        }

        // allow threads to start and deregister self
        phaser.arriveAndDeregister();
    }

    public static void main(String[] args) {

        Phaser phaser = new Phaser(1);

        List<Printer> tasks = Lists.newArrayList(new Printer(), new Printer(), new Printer());
        for (Printer task : tasks) {
            phaser.register();
            new Thread() {
                @Override
                public void run() {
                    phaser.arriveAndAwaitAdvance();
                    task.run();
                }
            }.start();
        }
        System.out.println("main wait");
        phaser.arriveAndDeregister();
    }

    public static class Printer implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread());
        }
    }
}

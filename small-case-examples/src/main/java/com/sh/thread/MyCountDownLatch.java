package com.sh.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * MyThread class
 *
 * @author lkn
 * @date 2017/11/14
 */
public class MyCountDownLatch implements Runnable {

    CountDownLatch latch;

    public MyCountDownLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " execute task. ");
            sleep(2);
            System.out.println(Thread.currentThread().getName() + " finished task. ");
        } finally {
            latch.countDown();
        }
    }


    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            MyCountDownLatch thread = new MyCountDownLatch(latch);
            thread.run();
        }

        System.out.println("main thread await. ");
        latch.await();
        System.out.println("main thread finishes await. ");
    }
}

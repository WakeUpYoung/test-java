package com.wy.test.concurrent;

import java.util.concurrent.CountDownLatch;

public class FirstThread implements Runnable {
    private final CountDownLatch latch;
    public FirstThread(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在运行");
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "运行结束");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

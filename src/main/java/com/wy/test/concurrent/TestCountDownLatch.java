package com.wy.test.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author Wang Yu
 * CountDownLatch 类可以监听线程的运行状态
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        Thread thread = new Thread(new FirstThread(latch));

        Thread thread1 = new Thread(new SecondThread(latch));

        thread.setName("线程1");
        thread1.setName("线程2");

        thread.start();
        thread1.start();
        try {
            System.out.println("等待两个线程执行条件结束");
            latch.await();
            System.out.println("两个线程执行完毕");
            System.out.println("继续执行主线程方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

package com.wy.test.concurrent.thevolatile;

/**
 * @author Wang Yu
 */
public class VolatileThread implements Runnable {
    TestVolatile testVolatile;

    public VolatileThread(TestVolatile testVolatile) {
        this.testVolatile = testVolatile;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " get x is " + testVolatile.getX());
    }
}

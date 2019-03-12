package com.wy.test.concurrent.thesync;

/**
 * @author Wang Yu
 */
public class ThreadForTest implements Runnable {
    private TestSync testSync;

    public ThreadForTest(TestSync testSync) {
        this.testSync = testSync;
    }

    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName() + " x is " + testSync.getX());
        testSync.add();
    }
}

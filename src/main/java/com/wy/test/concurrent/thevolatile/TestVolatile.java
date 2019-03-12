package com.wy.test.concurrent.thevolatile;

/**
 * @author Wang Yu
 */
public class TestVolatile {
    volatile int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static void main(String[] args) {
        TestVolatile testVolatile = new TestVolatile();
        Runnable volatileThread = new VolatileThread(testVolatile);
        for (int i = 0; i < 100; i ++){
            Thread thread = new Thread(volatileThread);
            thread.start();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        testVolatile.setX(testVolatile.getX() + 1);

    }

}

package com.wy.test.concurrent.thesync;

/**
 * @author Wang Yu
 */
public class TestSync {
    private volatile int x;

    public TestSync(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public synchronized void add(){
        x ++;
        System.out.println("current x is " + x);
    }

    public static void main(String[] args) {
        TestSync testSync = new TestSync(0);
        ThreadForTest threadForTest = new ThreadForTest(testSync);
        for (int i = 0; i < 100; i ++){
            new Thread(threadForTest, "Thread" + i ).start();
        }
    }
}

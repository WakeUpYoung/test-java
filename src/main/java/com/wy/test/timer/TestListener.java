package com.wy.test.timer;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Wang Yu
 */
public class TestListener {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                File file = new File("test.txt");
                try {
                    if (!file.exists()){
                        file.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(String.valueOf(Math.random()*1000.0 + 1));
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0L, 3000L);
    }
}

package com.wy.test.timer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Wang Yu
 */
public class Listener {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                File file = new File("test.txt");
                if (file.exists()){
                    try {
                        FileReader fileReader = new FileReader(file);
                        char[] chs = new char[1024];
                        int len = 0;
                        while ((len = fileReader.read(chs)) != -1){
                            System.out.println(new String(chs, 0, len));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0L, 1500L);
    }
}

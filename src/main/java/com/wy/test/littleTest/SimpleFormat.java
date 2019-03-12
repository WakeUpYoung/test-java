package com.wy.test.littleTest;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleFormat {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("输入：");
            String next = in.next();
            if (next.equals("exit")){
                System.exit(0);
            }
            String formatTableName = getFormatTableName(next);
            System.out.println("表名：" + formatTableName);
            System.out.println("主键约束：" + formatPK(formatTableName));
        }

    }

    public static String formatCase(String word){
        // 遍历字符串
        char[] chars = word.toCharArray();
        ArrayList<String> newChars = new ArrayList<>();
        for (int i = 0; i < chars.length; i++){
            if (!Character.isUpperCase(chars[i]) || i == 0){
                newChars.add(String.valueOf(chars[i]));
            } else {
                // 如果是大写字母，说明是新单词的开头
                String s = String.valueOf(chars[i]);
                newChars.add("_" + s);
            }
        }
        return String.join("", newChars).toUpperCase();
    }

    public static String getFormatTableName(String tableName){
        return "TSM_" + formatCase(tableName);
    }

    public static String formatPK(String formatTable){
        return "PK_" + formatTable + "_ID";
    }

    private void get(){
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("输入:");
            String next = in.next();
            if (next.equals("exit")){
                System.exit(0);
            }
            String result = formatCase(next).toUpperCase();
            System.out.println("format结果:" + result);
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable tText = new StringSelection(result);
            clip.setContents(tText, null);

        }
    }

}

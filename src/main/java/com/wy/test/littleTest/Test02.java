package com.wy.test.littleTest;

public class Test02 {
    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++){
            for (int j = 30; j >= 1; j--){
                if (i == j){
                    continue;
                }
                int res = i*j;
                System.out.println(i + "*" +j + "=" + res);
            }
        }
    }
}

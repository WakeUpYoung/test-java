package com.wy.test.chineseShape;

/**
 * 全角半角判断以及转换
 */
public class ChineseShape {
    private static final int OFFSET = 65248;
    private static final int MIN_DBC = (int)'0';
    private static final int MAX_DBC = (int)'9';
    private static final int MIN_SBC = (int)'０';
    private static final int MAX_SBC = (int)'９';
    private static final int DBC_COMMA = (int)',';
    private static final int SBC_COMMA = (int)'，';

    public static void printShape(){
        for (int i = 33 ; i <= 126; i++){
            System.out.println((char)(i+OFFSET));
        }
    }

    public static void test(){
        System.out.println((int)',');
        System.out.println((int)'，');
    }

    public static void test01(){
        String regx = "[^\\x00-\\xff]";
        System.out.println("１".matches(regx));
    }

    public static void test02(){
        System.out.println((int)'0');
        System.out.println((int)'9');
        System.out.println((int)',');
        System.out.println((int)'，');
    }

    public static void test03(String string){
        String[] array = string.split("[,，]");
        for (String anArray : array) {
            System.out.println(anArray);
        }
    }

    public static void main(String[] args) {
//        test();
//        printShape();
//        test01();
//        test02();

        test03("1,2,3，4");

    }
}

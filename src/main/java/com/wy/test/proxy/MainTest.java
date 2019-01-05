package com.wy.test.proxy;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) {
        JudgeProxy judgeProxy = new JudgeProxy();
        UserManager userManager;
        try {
            userManager = (UserManager) new JudgeProxy().getProxy("com.wy",new UserManagerImpl());
            userManager.addUser(1);
            userManager.deleteUser(1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void testProxy(){
        JDKProxy jdkProxy = new JDKProxy();
        UserManager userManager = (UserManager) jdkProxy.newProxy(new UserManagerImpl());
        userManager.addUser(1);
    }

    public static void testArrayList(){
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        int a = 1;
        int b = 2;
        int c = 3;
        try {
            myArrayList.add(a);
            myArrayList.add(b);
            myArrayList.add(c);
            for (int i=0;i<10;i++){
                myArrayList.add(a);
            }
            myArrayList.remove(10);
            myArrayList.set(0,2);
            System.out.println(myArrayList.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

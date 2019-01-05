package com.wy.test.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflexTest {
    public static void main(String[] args) {
        Dog dog = new Dog();
        long start = System.currentTimeMillis();
        Class clazz = dog.getClass();
        for (int i = 0; i < 1000000; i++){
            invokeSet(clazz, dog);
//            dog.setName("name");
        }
        long end = System.currentTimeMillis();
        double s = (end - start)/1000.0;
        System.out.println("invoke:" + s + "秒");
    }

    public static void invokeSet(Class clazz, Dog dog){
        try {
            Method method = clazz.getMethod("setName", String.class);
            method.invoke(dog, "name");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

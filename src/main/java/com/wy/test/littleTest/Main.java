package com.wy.test.littleTest;

public class Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.setA(1);
        child.setName("Hello");
        Object o = child;
        child = null;
        if (o instanceof Child){
            Child newChild = (Child) o;
            System.out.println(newChild.getName());
        }
    }
}

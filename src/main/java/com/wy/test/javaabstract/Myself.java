package com.wy.test.javaabstract;

public class Myself extends People {
    public Myself() {
        super();
    }

    public Myself(String name) {
        super(name);
    }

    @Override
    public void sayHello() {
        System.out.println("Hello,my name is "+name);
    }

}

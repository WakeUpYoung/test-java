package com.wy.test.javaabstract;

public abstract class People {
    protected String name;

    public People() {
    }

    public People(String name) {
        this.name = name;
    }

    public abstract void sayHello();

    public void sayName(){
        System.out.println("name is : "+ name );
    }

    public void myself(){
        sayHello();
        sayName();
    }
}

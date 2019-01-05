package com.wy.test.littleTest;

public class Child extends Father {
    private Integer a;
    private Integer b;
    private String name;

    @Override
    public Integer getA() {
        return a;
    }

    @Override
    public void setA(Integer a) {
        this.a = a;
    }

    @Override
    public Integer getB() {
        return b;
    }

    @Override
    public void setB(Integer b) {
        this.b = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.wy.designpattern.observer;

public class User implements IObserver{
    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    private void read(){
        System.out.println(name+"收到消息:"+message);
    }
}

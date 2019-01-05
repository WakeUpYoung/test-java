package com.wy.designpattern.observer;

public class Main {
    public static void main(String[] args) {
        MessageBox messageBox = new MessageBox();

        User user1 = new User("Tom");
        User user2 = new User("Peter");
        User user3 = new User("Catty");

        messageBox.registerObserver(user1);
        messageBox.registerObserver(user2);
        messageBox.registerObserver(user3);

        messageBox.setInfo("Hello");
    }
}

package com.wy.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class MessageBox implements Observerable {

    private List<IObserver> observerList = new ArrayList<>();
    private String message;

    @Override
    public void registerObserver(IObserver o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        if (!observerList.isEmpty())
            observerList.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (IObserver o :
                observerList) {
            o.update(message);
        }
    }

    public void setInfo(String message){
        this.message = message;
        System.out.println("setInfo: "+message);
        notifyObserver();
    }
}

package com.wy.designpattern.observer;

public interface Observerable {
    void registerObserver(IObserver o);

    void removeObserver(IObserver o);

    void notifyObserver();
}


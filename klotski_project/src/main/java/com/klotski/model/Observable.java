package com.klotski.model;

public interface Observable {
    public void addListener(Observer obs);
    public void removeListener(Observer obs);
    public void notifyListener();
}

package com.klotski.model;

public interface Observer {
    public void update(int movesCounter, boolean win);
}

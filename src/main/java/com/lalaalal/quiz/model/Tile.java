package com.lalaalal.quiz.model;

public class Tile {
    final int OPEN_TYPE;
    private boolean open;

    Tile(int OPEN_TYPE) {
        this.OPEN_TYPE = OPEN_TYPE;
        open = true;
    }

    boolean isOpen() {
        return open;
    }

    void toggleOpen() {
        open = !open;
    }
}

package com.lalaalal.quiz.model;

import java.util.Random;

public class Pos {
    private int x;
    private int y;

    public static Pos[] randPosArray(int num, Range xRange, Range yRange) throws Exception {
        Pos[] posArr = new Pos[num];
        Random random = new Random();

        for (int i = 0; i < num; i++) {
            int x = Math.abs(random.nextInt()) % xRange.getRange() + xRange.getFrom();
            int y = Math.abs(random.nextInt()) % yRange.getRange() + yRange.getFrom();

            posArr[i] = new Pos(x, y);
            for (int j = 0; j < i; j++) {
                if (posArr[j].equals(posArr[i])) {
                    i--;
                    break;
                }
            }
        }

        return posArr;
    }

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Pos pos) {
        return x == pos.x && y == pos.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

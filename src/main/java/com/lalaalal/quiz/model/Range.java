package com.lalaalal.quiz.model;

public class Range {
    private int from;
    private int to;

    public static boolean inRange(int num, int from, int to) throws Exception {
        if (from > to)
            throw new Exception("'from' must smaller than 'to'");
        return from <= num && num <= to;
    }

    public static boolean inRange(int num, Range range) throws Exception {
        return inRange(num, range.from, range.to);
    }

    public Range(int from, int to) throws Exception {
        if (from > to)
            throw new Exception("'from' must smaller than 'to'");

        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getRange() {
        return to - from;
    }
}

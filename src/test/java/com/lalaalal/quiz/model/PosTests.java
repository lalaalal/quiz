package com.lalaalal.quiz.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PosTests {
    @Test
    void randomPosArrayTest() {
        try {
            Range range = new Range(0, 3);
            Pos[] arr = Pos.randPosArray(2, range, range);

            for (int i = 0; i < 2; i++) {
                System.out.println(arr[i]);
                assertTrue(Range.inRange(arr[i].getX(), range));
                assertTrue(Range.inRange(arr[i].getY(), range));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

package com.lalaalal.quiz.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RangeTests {
    @Test
    void inRangeTest() {
        try {
            assertTrue(Range.inRange(0, 0, 3));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

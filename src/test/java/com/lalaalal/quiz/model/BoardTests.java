package com.lalaalal.quiz.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BoardTests {
    @Test
    void BoardOpenTest() {
        try {
            Board board = new Board(4, 4, 3);
            board.openTile(new Pos(0, 0));
            board.displayBoard();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

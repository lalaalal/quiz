package com.lalaalal.quiz.model;

import java.util.Random;

public class Board {
    private Tile[][] map;

    public final int ROW;
    public final int COL;

    private static boolean[][][] opener = {
            {
                    { false, true, false },
                    { true, true, true },
                    { false, true, false }
            },
            {
                    { false, true, false },
                    { false, true, false },
                    { false, true, false }
            },
            {
                    { false, false, false },
                    { true, true, true },
                    { false, false, false }
            },
            {
                    { true, true, true },
                    { false, true, false },
                    { false, true, false }
            }
    };

    static final int NUM_OPEN_TYPE = opener.length;

    public Board(int row, int col, int openTypeNum) {
        ROW = row;
        COL = col;

        Random random = new Random();
        map = new Tile[COL][ROW];

        for (int y = 0; y < COL; y++) {
            for (int x = 0; x < ROW; x++) {
                map[y][x] = new Tile(Math.abs(random.nextInt() % openTypeNum));
            }
        }
    }

    public void openTile(Pos pos) throws Exception {
        int openType = getOpenType(pos);
        for (int y = -1; y < 2; y++) {
            int openYPos = pos.getY() + y;
            if (!Range.inRange(openYPos, 0, COL - 1))
                continue;
            for (int x = -1; x < 2; x++) {
                int openXPos = pos.getX() + x;
                if (!Range.inRange(openXPos, 0, ROW - 1))
                    continue;
                if (opener[openType][y + 1][x + 1])
                    toggleTile(openXPos, openYPos);
            }
        }
    }

    public boolean allTilesAreOpened() {
        for (int y = 0; y < COL; y++) {
            for (int x = 0; x < ROW; x++) {
                if (!map[y][x].isOpen())
                    return false;
            }
        }
        return true;
    }

    private void toggleTile(Pos pos) {
        toggleTile(pos.getX(), pos.getY());
    }

    private void toggleTile(int x, int y) {
        map[y][x].toggleOpen();
    }

    public boolean isOpen(Pos pos) {
        return isOpen(pos.getX(), pos.getY());
    }

    public boolean isOpen(int x, int y) {
        return map[y][x].isOpen();
    }

    private int getOpenType(Pos pos) {
        Tile tile = map[pos.getY()][pos.getX()];
        return tile.OPEN_TYPE;
    }

    // Debug
    public void displayBoard() {
        for (int y = 0; y < COL; y++) {
            for (int x = 0; x < ROW; x++) {
                System.out.print(((map[y][x].isOpen()) ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }
}

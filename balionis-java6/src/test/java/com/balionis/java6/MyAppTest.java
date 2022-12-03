package com.balionis.java6;

import org.junit.Test;

import static org.junit.Assert.*;

/** */
public class MyAppTest {

    MyApp app = new MyApp();

    @Test
    public void testFindSudoku() {
        byte[][] expected = {
                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9},
        };

        byte[][] board = {
                {0,3,4,6,7,8,9,1,2},
                {6,0,2,1,9,5,3,4,8},
                {1,9,0,3,4,2,5,6,7},
                {8,5,9,0,6,1,4,2,3},
                {4,2,6,8,0,3,7,9,1},
                {7,1,3,9,2,0,8,5,6},
                {9,6,1,5,3,7,0,8,4},
                {2,8,7,4,1,9,6,0,5},
                {3,4,5,2,8,6,1,7,0},
        };

        byte[][] actual = app.findSudoku(board);

        for (int i=0; i < board.length; i++) {
            assertArrayEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testFindMissing() {
        byte[] line = {0,3,4,6,7,8,9,0,2};

        byte[] expected = {1,5};
        byte[] actual = app.findMissing(line);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHasSudokuSuccess() {

        byte[][] board = {
                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9},
        };

        boolean actual = app.hasSudoku(board);

        assertTrue(actual);
    }

    @Test
    public void testHasSudokuFailure() {

        byte[][] board = {
                {0,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9},
        };

        boolean actual = app.hasSudoku(board);

        assertFalse(actual);
    }
}

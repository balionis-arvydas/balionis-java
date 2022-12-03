package com.balionis.java6;

import java.util.Arrays;

public class MyApp {

    public byte[][] findSudoku(byte[][] board) {
        return findSudoku(board, 0);
    }

    public byte[][] findSudoku(byte[][] board, int row) {

        if (row >= board.length) {
            return board;
        }

        byte[] line = findMissing(board[row]);
        if (line.length > 0) {
            for (int i=0; i < line.length; i++) {
                byte[] newLine = buildLine(board[row], line[i]);
                byte[][] newBoard = copyBoard(board);
                newBoard[row] = newLine;
                if (hasSudoku(newBoard)) {
                    return newBoard;
                } else {
                    newBoard = findSudoku(newBoard, row);
                    if (hasSudoku(newBoard)) {
                        return newBoard;
                    }
                }
            }
        } else {
            return findSudoku(board, row + 1);
        }
        return board;
    }

    public byte[][] copyBoard(byte[][] board) {
        byte[][] res = new byte[board.length][0];

        for (int i=0; i < board.length; i++) {
            res[i] = Arrays.copyOf(board[i], board.length);
        }

        return res;
    }

    public byte[] buildLine(byte[] line, byte n) {
        byte[] res = Arrays.copyOf(line, line.length);
        for (int i=0; i < res.length; i++) {
            if (res[i] <= 0) {
                res[i] = n;
                return res;
            }
        }
        return res;
    }

    public byte[] findMissing(byte[] line) {
        byte[] missing = {1,2,3,4,5,6,7,8,9};

        int n = 0;

        for (int i=0; i < line.length; i++) {
            if (line[i] > 0) {
                missing[line[i]-1] = 0;
                n++;
            }
        }

        byte[] res = new byte[9 - n];

        n = 0;
        for (int i=0; i < missing.length; i++) {
            if (missing[i] > 0) {
                res[n] = missing[i];
                n++;
            }
        }
        return Arrays.copyOf(res, n);
    }

    public boolean hasSudoku(byte [][] board) {
        for (int i=0; i<board.length; i++) {
            if (!has45(board, i, 0, i+1, 9)) {
                return false;
            }
            if (!has45(board, 0, i, 9, i+1)) {
                return false;
            }
        }

        for (int i=0; i<board.length; i+=3) {
            for (int j=0; j<board.length; j+=3) {
               if (!has45(board, i, j, i+3, j+3)) {
                   return false;
               }
            }
        }

        return true;
    }

    public boolean has45(byte[][] board, int minX, int minY, int maxX, int maxY) {
        int sum = 0;
        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                sum += board[i][j];
            }
        }
        return sum == 45;
    }

    public static void main(String[] args) {
        System.out.println("main: args=" + Arrays.toString(args));

        System.out.println("main: done");
    }

}


package com.balionis.java5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// use operators +, - , *, /, and || to get the maximum and minimum of two numbers
public class MyApp6 {

    public static void main(String[] args) {

        System.out.println("main: max(3,1)=" + max(3, 1));

        System.out.println("main: max(1,3)=" + max(1, 3));

        System.out.println("main: max(3,0)=" + max(3, 0));

        System.out.println("main: max(-3,-1)=" + max(-3, -1));

        System.out.println("main: max(-1,-3)=" + max(-1, -3));

        System.out.println("main: max(-3,0)=" + max(-3, 0));

        System.out.println("main: done");
    }

    static int max(int x, int y) {
        if (isNegative(x)) {
            if (isNegative(y)) {
                return isGreater(-x, -y) ? y : x;
            } else {
                return y;
            }
        } else if (isNegative(y)) {
            return x;
        } else {
            return isGreater(x, y) ? x : y;
        }
    }

    static boolean isNegative(int x) {
        return 1 + (x >> 31) - (-x >> 31) == 0;
    }

    static boolean isGreater(int x, int y) {
        int r = y == 0 ? 1 : x / y;
        return (r != 0);
    }
}


package com.balionis.java5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// compute snow among hills
public class MyApp4 {

    public static void main(String[] args) {

        int[] hills1 = {1, 2, 1, 3, 0, 2, 0};
        System.out.println("main: test1(3)=" + test(hills1));

        int[] hills2 = {1, 2, 1};
        System.out.println("main: test2(0)=" + test(hills2));

        int[] hills3 = {3, 2, 1};
        System.out.println("main: test3(0)=" + test(hills3));

        int[] hills4 = {3, 1, 2};
        System.out.println("main: test4(1)=" + test(hills4));

        System.out.println("main: done");
    }

    static long test(int[] hills) {

        if (hills.length < 3) {
            return 0;
        }

        List<Integer> hillList = Arrays.stream(hills).mapToObj((x) -> new Integer(x)).collect(Collectors.toList());

        List<List<Character>> landscape = build(hillList);

        List<List<Character>> melted = melt(landscape);

        return count(melted);
    }

    static List<List<Character>> build(List<Integer> hills) {
        int max = hills.stream().max((a, b) -> a - b).get();

        return hills.stream().map((hill) -> {
            Stream<Character> snow =
                    IntStream.range(0, max - hill)
                             .mapToObj((x) -> new Character('*'));
            Stream<Character> stone =
                    IntStream.range(0, hill)
                            .mapToObj((x) -> new Character('x'));

            return Stream.concat(snow, stone).collect(Collectors.toList());
        }).collect(Collectors.toList());
    }

    static List<List<Character>> melt(List<List<Character>> landscape) {
        int x = landscape.size();
        int y = landscape.get(0).size();
        for (int j=0; j < y; j++) {
            for (int i=0; i < x; i++) {
                if (landscape.get(i).get(j) == '*') {
                    landscape.get(i).set(j, ' ');
                } else {
                    break;
                }
            }
            for (int i=x-1; i >=0; i--) {
                if (landscape.get(i).get(j) == '*') {
                    landscape.get(i).set(j, ' ');
                } else {
                    break;
                }
            }
        }
        return landscape;
    }

    static long count(List<List<Character>> landscape) {
        return landscape.stream().flatMap((x) -> x.stream()).filter((x) -> x == '*').count();
    }
}


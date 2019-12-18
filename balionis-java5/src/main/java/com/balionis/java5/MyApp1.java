package com.balionis.java5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// what is the least number of ways to get from number n to 1, using operations (divide by) /2, /3, and -1
public class MyApp1 {

    public static void main(String[] args) {

        System.out.println("main: test=" + find(10));

        System.out.println("main: done");
    }

    static List<String> find(int n) {
        List<List<String>> lines = build(n);
        List<String> start = new ArrayList<>();
        List<String> res = lines.stream().reduce(start, (List<String> acc, List<String> line) -> {
           if (acc.isEmpty()) {
               return line;
           } else if (line.size() < acc.size()) {
               return line;
           }
           return acc;
        });
        return res;
    }

    static List<List<String>> build(int n, String op) {
        List<List<String>> lines = build(n);
        if (lines.isEmpty()) {
            List<String> line = new ArrayList<>();
            line.add(op);
            lines.add(line);
        } else {
            for (List<String> line : lines) {
                line.add(0, op);
            }
        }
        return lines;
    }

    static List<List<String>> build(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 1) {
            return res;
        }

        if (n % 3 == 0) {
            List<List<String>> lines = build(n / 3, "/3");
            res.addAll(lines);
        }

        if (n % 2 == 0) {
            List<List<String>> lines = build(n / 2, "/2");
            res.addAll(lines);
        }

        List<List<String>> lines = build(n - 1, "-1");
        res.addAll(lines);

        return res;
    }

}


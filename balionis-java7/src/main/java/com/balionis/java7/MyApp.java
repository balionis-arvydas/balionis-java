package com.balionis.java7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MyApp {

    public List<Pair> merge(List<Pair> pairs) {
        return pairs.stream()
                .sorted(Comparator.comparing(e -> e.start))
                .reduce(new ArrayList<>(), MyApp::merge, MyApp::merge);
    }

    public static ArrayList<Pair> merge(ArrayList<Pair> acc, Pair p) {
        if (acc.isEmpty()) {
            acc.add(p);
            return acc;
        }
        Pair last = acc.get(acc.size() - 1);
        if (last.end < p.start) {
            acc.add(p);
            return acc;
        } else {
            last.end = Math.max(last.end, p.end);
        }
        return acc;
    }

    public static ArrayList<Pair> merge(ArrayList<Pair> acc1, ArrayList<Pair> acc2) {
        acc1.addAll(acc2);
        return acc1;
    }

    public static void main(String[] args) {
        System.out.println("main: args=" + Arrays.toString(args));

        System.out.println("main: done");
    }

    public static class Pair {
        private int start;
        private int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "[" + start + "," + end + "]";
        }

        public boolean equals(Object obj) {
            return equals((Pair)obj);
        }

        public boolean equals(Pair obj) {
            return (this.start == obj.start) && (this.end == obj.end);
        }
    }


}


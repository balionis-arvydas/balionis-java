package com.balionis.java5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// for n random positive and negative numbers in an array,
// how does one pick a set of numbers that give the largest sum,
// subject to the following constraints: cannot pick numbers which are adjacent in position in the array
public class MyApp5 {

    static class MySum {
        Set<Integer> line;
        Integer sum;

        public MySum(Set<Integer> line, Integer sum) {
            this.sum = sum;
            this.line = line;
        }

        @Override
        public String toString() {
            return "{ sum=" + sum + ", line=" + line + "}";
        }
    }

    public static void main(String[] args) {

        List<Integer> data = new ArrayList<>();
        data.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));

        System.out.println("main: build=" + test(data));

        System.out.println("main: done");
    }

    static MySum test(List<Integer> vl) {
        List<MySum> sl = sum(vl);

        MySum res = sl.stream().reduce(null, (acc, v) -> {
            if (acc == null) {
                return v;
            }
            if (acc.sum < v.sum) {
                return v;
            }
            return acc;
        });

        return res;
    }

    static List<MySum> sum(List<Integer> vl) {
        Set<Integer> il = IntStream.range(0, vl.size()).mapToObj(x -> x).collect(Collectors.toSet());

        List<Set<Integer>> cl = build(il);

        List<MySum> res = cl.stream().map((Set<Integer> x) -> {
            int sum = x.stream().map((i) -> vl.get(i)).collect(Collectors.summingInt(Integer::intValue));
            return new MySum(x, sum);
        }).collect(Collectors.toList());

        return res;
    }

    static List<Set<Integer>> build(Set<Integer> il) {
        List<Set<Integer>> res = new ArrayList<>();
        for (Integer i: il) {
            Set<Integer> curr = new HashSet<>();
            curr.add(i);
            res.add(curr);

            Set<Integer> ril = new HashSet<>(il);
            ril.remove(i);
            ril.remove(i-1);
            ril.remove(i+1);
            List<Set<Integer>> innerList = build(ril);

            for (Set<Integer> inner: innerList) {
                inner.add(i);
            }
            res.addAll(innerList);
        }
        return res;
    }
}


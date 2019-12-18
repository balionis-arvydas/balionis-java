package com.balionis.java5;

import java.util.*;
import java.util.stream.Collectors;

// count in how many ways I can add English coins to the desired amount
public class MyApp9 {

    public static void main(String[] args) {

        int[] coins = {1, 2, 5, 10, 20, 50, 100};

        List<List<Integer>> change = test(6, coins);
        System.out.println("main: change(" + change.size() + ")=[");
        change.forEach((x) -> System.out.println(x + ","));
        System.out.println("]");

        System.out.println("main: done");
    }

    static List<List<Integer>> test(int amount, int[] coins) {
        List<List<Integer>> choices = new ArrayList<>();
        build(amount, coins, new ArrayList<>(), choices);
        List<List<Integer>> res =
            choices.stream()
                .map((x) -> x.stream().sorted().collect(Collectors.toList()))
                .distinct()
                .collect(Collectors.toList());
        return res;
    }

    static void build(int amount, int[] coins, List<Integer> curr, List<List<Integer>> aggr) {
        for (int i=0; i < coins.length; i++) {
            if (amount - coins[i] > 0) {
                List<Integer> next = new ArrayList<>(curr);
                next.add(coins[i]);
                build(amount - coins[i], coins, next, aggr);
            } else if (amount - coins[i] == 0) {
                curr.add(coins[i]);
                aggr.add(curr);
            }
        }
    }
}


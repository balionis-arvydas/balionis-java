package com.balionis.java0;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MyApp {

    public static void main(String[] args) throws Exception {

        System.out.println("main: args=" + Arrays.toString(args));
        // TODO
        System.out.println("main: done");
    }

    public Long maxValue(List<Long> values) {
        return values.stream().max(Comparator.naturalOrder()).orElse(Long.MIN_VALUE);
    }
}


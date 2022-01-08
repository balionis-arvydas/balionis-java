package com.balionis.java0;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyApp {

    public static void main(String[] args) {

        System.out.println("main: args=" + Arrays.toString(args));

        MyApp app = new MyApp();
        String res = app.solution(Arrays.asList(args));
        System.out.println("main: res=" + res);

        System.out.println("main: done");
    }

    public String solution(List<String> values) {
        return values.stream().collect(Collectors.joining());
    }
}


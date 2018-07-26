package com.balionis.docker1;

import java.util.Arrays;

public class MyApp {

    public static void main(String[] args) throws Exception {

        System.out.println("main: args=" + Arrays.toString(args));

        if (args.length < 1) {
            usage();
            System.exit(1);
        }

        System.out.println("main: done");
    }

    public static void usage() {
        System.out.println("usage: {filename}");
    }

}


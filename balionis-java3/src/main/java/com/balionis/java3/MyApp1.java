package com.balionis.java3;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.EnumSet;

import java.util.stream.Collector;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyApp1 implements Runnable {

    private static final Log LOGGER = LogFactory.getLog(MyApp1.class);
	
	private String name;

    public MyApp1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int num1 = name.chars()
                       .filter(c -> c >= '0' && c <= '9')
                       .reduce(0, (amount, c) -> (amount * 10 + (c - '0')));

        LOGGER.info("run: name=" + name + ", num1=" + num1 + " done...");
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
            System.exit(2);
        }
		
		String name = args[0];

        LOGGER.info("main: name=" + name);

		MyApp1 myApp = new MyApp1(name);
		
		myApp.run();

        LOGGER.info("main: done");
    }

    public static void usage() {
        System.out.println("usage: {name}");
    }
}

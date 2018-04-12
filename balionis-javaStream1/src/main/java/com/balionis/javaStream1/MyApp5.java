package com.balionis.javaStream1;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyApp5 {

    public static class Tuple {
        public final Character c;
        public final Integer i;

        public Tuple(Character c, Integer i) {
            this.c = c;
            this.i = i;
        }

        @Override
        public String toString() {
            return c + "=" + i;
        }
    }

    private static final Log LOGGER = LogFactory.getLog(MyApp5.class);

    public static void main(String[] args) {

        if (args.length < 1) {
            usage();
            System.exit(2);
        }
	
        LOGGER.info("main: args=" + Arrays.toString(args));
		
		String msg = args[0];

        List<Tuple> numbers = msg.chars().mapToObj(x -> new Tuple(new Character((char)x), new Integer(x))).collect(Collectors.toList());

        LOGGER.info("main: numbers=" + numbers);

        LOGGER.info("main: done");
    }

    public static void usage() {
        System.out.println("usage: {msg}");
    }	
}

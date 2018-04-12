package com.balionis.javaStream1;

import java.util.*;

import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyApp3 implements Runnable {

    public static class MyCollector
            implements Collector<Integer, Map<Integer, Integer>, Map<Integer, Integer>> {

        private int bucketSize;

        public MyCollector(int bucketSize) {
            this.bucketSize = bucketSize;
        }

        @Override
        public Supplier<Map<Integer, Integer>> supplier() {
            return HashMap::new;
        }

        @Override
        public BiConsumer<Map<Integer, Integer>, Integer> accumulator() {
            return (map, val) -> map.merge((int)(val / bucketSize), 1,
                    (a, b) -> a + 1);
        }

        @Override
        public Function<Map<Integer, Integer>, Map<Integer, Integer>> finisher() {
            return Function.identity();
        }

        @Override
        public BinaryOperator<Map<Integer, Integer>> combiner() {
            return (map1, map2) -> {
                map2.forEach((k, v) -> map1.merge(k, v, (v1, v2) -> v1 + v2));
                return map1;
            };
        }

        @Override
        public Set<Collector.Characteristics> characteristics() {
            return EnumSet.noneOf(Collector.Characteristics.class);
        }

        public static MyCollector toCollector(int bucketSize) {
            return new MyCollector(bucketSize);
        }

    }

    private static final Log LOGGER = LogFactory.getLog(MyApp3.class);
	
	private String name;
	private int buckets;

    public MyApp3(String name, int buckets) {
        this.name = name;
		this.buckets = buckets;
    }

    public void run() {

        List<Integer> numbers = name.chars().mapToObj(x -> new Integer(x)).collect(Collectors.toList());

        Map<Integer, Integer> histogram = numbers.stream().collect(MyCollector.toCollector(buckets));

        LOGGER.info("run: name=" + name + ", histogram=" + histogram.toString() + " done...");
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            usage();
            System.exit(2);
        }
		
		String name = args[0];
		int buckets = Integer.parseInt(args[1]);

        LOGGER.info("main: name=" + name + ", buckets=" + buckets);

		MyApp3 myApp = new MyApp3(name, buckets);
		
		myApp.run();

        LOGGER.info("main: done");
    }

    public static void usage() {
        System.out.println("usage: {name} {buckets}");
    }
}

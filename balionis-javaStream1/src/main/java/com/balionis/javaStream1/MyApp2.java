package com.balionis.javaStream1;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

import java.util.stream.Collector;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyApp2 implements Runnable {

    public static class MyCollector
            implements Collector<Double, Map<Integer, Integer>, Map<Integer, Integer>> {

        private int bucketSize;

        public MyCollector(int bucketSize) {
            this.bucketSize = bucketSize;
        }

        @Override
        public Supplier<Map<Integer, Integer>> supplier() {
            return HashMap::new;
        }

        @Override
        public BiConsumer<Map<Integer, Integer>, Double> accumulator() {
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

    private static final Log LOGGER = LogFactory.getLog(MyApp2.class);
	
    public void run() {

        List<Double> numbers = Arrays.asList(1.0, 1.1, 1.4, 1.7, 5.3, 5.4, 9.9);
        Map<Integer, Integer> histogram = numbers.stream().collect(MyCollector.toCollector(2));

        LOGGER.info("run: histogram=" + histogram.toString() + " done...");
    }

    public static void main(String[] args) {
		
        LOGGER.info("main: args=" + Arrays.toString(args));

		MyApp2 myApp = new MyApp2();
		
		myApp.run();

        LOGGER.info("main: done");
    }

}

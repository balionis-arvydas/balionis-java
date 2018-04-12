package com.balionis.javaStream1;

import java.util.*;

import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyApp4 {

    private static final Log LOGGER = LogFactory.getLog(MyApp4.class);

    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

    public static void main(String[] args) {

        LOGGER.info("main: args=" + Arrays.toString(args));

        int[] data = {1,1,1,2,3,2,4,5,1,1,2};

        Stream<Integer> stream =
            Arrays.stream(data)
                  .distinct()
                  .mapToObj(x -> new Integer(x))
                  .sorted(new MyComparator());

        Optional<Integer> second = stream.skip(1).findFirst();

        if (second.isPresent()) {
            LOGGER.info("main: dataList[second]=" + second.get());
        } else {
            LOGGER.info("main: no second largest");
        }

        LOGGER.info("main: done");
    }

}

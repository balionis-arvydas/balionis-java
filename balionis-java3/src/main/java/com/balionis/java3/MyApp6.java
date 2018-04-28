package com.balionis.java3;

import java.util.*;
import java.util.stream.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyApp6 {

    private static final Log LOGGER = LogFactory.getLog(MyApp6.class);

    public static class Student {
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return name + "=" + score;
        }
    }

    public static int getMaxV1(String[][] data) {
        List<Student> students = new ArrayList<>();
        for (int i=0; i<data.length; i++) {
            students.add(new Student(data[i][0], Integer.parseInt(data[i][1])));
        }

        Map<String, Double> byName =
                students.stream().collect(
                        Collectors.groupingBy(Student::getName,
                                Collectors.averagingInt(Student::getScore)));

        LOGGER.info("getMaxV1: byName=" + byName);

        OptionalInt optMax = byName.entrySet().stream().map(Map.Entry::getValue).mapToInt(x -> (int) Math.floor(x)).max();

        int max = optMax.orElse(0);

        LOGGER.info("getMaxV1: max=" + max);

        return max;
    }

    public static int getMaxV0(String[][] data) {
        Map<String, List<Integer>> students = new HashMap<>();

        for (int i=0; i<data.length; i++) {
            String name = data[i][0];
            Integer score = new Integer(data[i][1]);

            if (students.containsKey(name)) {
                students.get(name).add(score);
            } else {
                List<Integer> scores = new ArrayList<>();
                scores.add(score);
                students.put(name, scores);
            }
        }

        LOGGER.info("getMaxV0: students=" + students);

        Map<String, Integer> averages = new HashMap<>();
        for(Map.Entry<String, List<Integer>> e : students.entrySet()) {
            int sum = 0;
            for (Integer i : e.getValue()) {
                sum += i;
            }

            int avg = (int) Math.floor(sum / e.getValue().size());
            averages.put(e.getKey(), avg);
        }

        LOGGER.info("getMaxV0: averages=" + averages);

        int max = Integer.MIN_VALUE;
        for(Map.Entry<String, Integer> e : averages.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
            }
        }

        LOGGER.info("getMaxV0: max=" + max);

        return max;
    }


    public static void main(String[] args) {

        LOGGER.info("main: args=" + Arrays.toString(args));

        String[][] data = {{"Charlie", "-10"}, {"Charlie", "-5"},
                           {"John", "-1"}, {"John", "-5"}};

        int max0 = getMaxV0(data);

        LOGGER.info("main: max0=" + max0);

        int max1 = getMaxV1(data);

        LOGGER.info("main: max1=" + max1);

        LOGGER.info("main: done");
    }
}

package com.balionis.javaStream1;

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

    public static int getMax(String[][] data) {
        List<Student> students = new ArrayList<>();
        for (int i=0; i<data.length; i++) {
            students.add(new Student(data[i][0], Integer.parseInt(data[i][1])));
        }

        Map<String, Double> byName =
                students.stream().collect(
                        Collectors.groupingBy(Student::getName,
                                Collectors.averagingInt(Student::getScore)));

        OptionalInt max = byName.entrySet().stream().map(Map.Entry::getValue).mapToInt(x -> (int) Math.floor(x)).max();

        return max.isPresent() ? max.getAsInt() : 0;
    }

    public static void main(String[] args) {

        LOGGER.info("main: args=" + Arrays.toString(args));

        String[][] data = {{"Charlie", "-10"}, {"Charlie", "-5"},
                           {"John", "-1"}, {"John", "-5"}};

        int max = getMax(data);

        LOGGER.info("main: max=" + max);

        LOGGER.info("main: done");
    }
}

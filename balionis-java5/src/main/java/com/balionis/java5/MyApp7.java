package com.balionis.java5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// design an algorithm to check if a graph is hamiltonian or not
public class MyApp7 {

    static class MyPoint {
        String name;
        Set<MyPoint> links;

        public MyPoint(String name) {
            this.name = name;
            this.links = new HashSet<>();
        }

        @Override
        public String toString() {
            return "{ name=" + name + ", links=" + links.stream().map((x) -> x.name).collect(Collectors.joining(",")) + " }";
        }
    }

    public static void main(String[] args) {

        Set<MyPoint> graph1 = buildGraph1();
        System.out.println("main: graph1=" + graph1 + ", test(graph1)=" + test(graph1));

        Set<MyPoint> graph2 = buildGraph2();
        System.out.println("main: graph2=" + graph2 + ", test(graph2)=" + test(graph2));

        Set<MyPoint> graph3 = buildGraph3();
        System.out.println("main: graph3=" + graph3 + ", test(graph3)=" + test(graph3));

        System.out.println("main: done");
    }

    static boolean test(Set<MyPoint> available) {
        for (MyPoint p : available) {
            boolean res = test(p, p, new HashSet<>(available), new HashSet<>());
            if (res) {
                return true;
            }
        }
        return false;
    }

    static boolean test(MyPoint first, MyPoint point, Set<MyPoint> available, Set<MyPoint> visited) {
        if (available.isEmpty()) {
            return first == point;
        }
        for (MyPoint p : point.links) {
            if (!visited.contains(p)) {
                Set<MyPoint> available2 = new HashSet<>(available);
                available2.remove(p);
                Set<MyPoint> visited2 = new HashSet<>(visited);
                visited2.add(p);
                boolean res = test(first, p, available2, visited2);
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    static Set<MyPoint> buildGraph1() {
        Set<MyPoint> graph = new HashSet<>();

        MyPoint p1 = new MyPoint("1");
        MyPoint p2 = new MyPoint("2");
        MyPoint p3 = new MyPoint("3");

        p1.links.addAll(Arrays.asList(p2, p3));
        p2.links.addAll(Arrays.asList(p1, p3));
        p3.links.addAll(Arrays.asList(p1, p2));

        graph.addAll(Arrays.asList(p1, p2, p3));

        return graph;
    }

    static Set<MyPoint> buildGraph2() {
        Set<MyPoint> graph = new HashSet<>();

        MyPoint p1 = new MyPoint("1");
        MyPoint p2 = new MyPoint("2");
        MyPoint p3 = new MyPoint("3");

        p1.links.addAll(Arrays.asList(p2));
        p2.links.addAll(Arrays.asList(p1, p3));
        p3.links.addAll(Arrays.asList(p2));

        graph.addAll(Arrays.asList(p1, p2, p3));

        return graph;
    }

    static Set<MyPoint> buildGraph3() {
        Set<MyPoint> graph = new HashSet<>();

        MyPoint p1 = new MyPoint("1");
        MyPoint p2 = new MyPoint("2");
        MyPoint p3 = new MyPoint("3");
        MyPoint p4 = new MyPoint("4");

        p1.links.addAll(Arrays.asList(p2, p3));
        p2.links.addAll(Arrays.asList(p1, p3));
        p3.links.addAll(Arrays.asList(p1, p2));

        graph.addAll(Arrays.asList(p1, p2, p3, p4));

        return graph;
    }
}


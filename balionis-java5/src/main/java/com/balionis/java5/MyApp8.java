package com.balionis.java5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// to find the pieces of land (aka islands) where:
// - a piece of land is marked with a 1.
// - any adjacent 1 (up, left, right, bottom) is part of the island.
public class MyApp8 {

    static class MyPoint {
        int x;
        int y;

        public MyPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{ " + (y + 1) + ", " + (x + 1) + " }";
        }
    }

    public static void main(String[] args) {

        byte[][] world = {
            { 0, 0, 0, 1, 1, 0, 0},
            { 0, 0, 1, 1, 1, 0, 0},
            { 0, 0, 0, 1, 0, 0, 0},
            { 0, 0, 1, 0, 0, 0, 0},
            { 0, 1, 1, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 1, 1},
            { 0, 1, 0, 0, 0, 1, 1}
        };

        List<Set<MyPoint>> islands = test(world);
        System.out.println("main: islands=" + islands);

        System.out.println("main: islands=" + islands.size());
        print(world, islands);

        System.out.println("main: done");
    }

    static List<Set<MyPoint>> test(byte[][] world) {
        List<Set<MyPoint>> islands = new ArrayList<>();
        for (int i=0; i<world.length; i++) {
            for (int j=0; j<world[i].length; j++) {
                if (world[i][j] == 1) {
                    if (!visited(i, j, islands)) {
                        MyPoint p = new MyPoint(i, j);
                        Set<MyPoint> island = new HashSet<>();
                        island.add(p);
                        islands.add(island);
                        explore(p, island, islands, world);
                    }
                }
            }
        }
        return islands;
    }

    static boolean visited(int x, int y, List<Set<MyPoint>> islands) {
        return islands.stream().flatMap((o) -> o.stream()).filter((o) -> o.x == x && o.y == y).count() > 0;
    }

    static void explore(int x, int y, Set<MyPoint> island, List<Set<MyPoint>> islands, byte[][] world) {
        if (!visited(x, y, islands)) {
            if (world[x][y] == 1) {
                MyPoint next = new MyPoint(x, y);
                island.add(next);
                explore(next, island, islands, world);
            }
        }
    }

    static void explore(MyPoint point, Set<MyPoint> island, List<Set<MyPoint>> islands, byte[][] world) {
        if (point.x > 0) {
            explore(point.x - 1, point.y, island, islands, world);
        }
        if (point.x < world.length - 1) {
            explore(point.x + 1, point.y, island, islands, world);
        }
        if (point.y > 0) {
            explore(point.x, point.y - 1, island, islands, world);
        }
        if (point.y < world[point.x].length - 1) {
            explore(point.x, point.y + 1, island, islands, world);
        }
    }

    static void print(byte[][] world, List<Set<MyPoint>> islands) {
        byte ic = 1;
        for (Set<MyPoint> island: islands) {
            for (MyPoint p : island) {
                world[p.x][p.y] = ic;
            }
            ic++;
        }
        for (int i=0; i<world.length; i++) {
            for (int j=0; j<world[i].length; j++) {
                System.out.print(world[i][j]);
            }
            System.out.println();
        }
    }
}


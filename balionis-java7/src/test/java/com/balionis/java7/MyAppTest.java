package com.balionis.java7;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/** */
public class MyAppTest {

    MyApp app = new MyApp();

    @Test
    public void testSample1() {
        List<MyApp.Pair> pairs = List.of(new MyApp.Pair(1, 7), new MyApp.Pair(3, 9));
        System.out.println("pairs=" + pairs);

        List<MyApp.Pair> actual = app.merge(pairs);
        System.out.println("actual=" + actual);

        List<MyApp.Pair> expected =  List.of(new MyApp.Pair(1, 9));

        assertEquals(expected, actual);
    }

    @Test
    public void testSample2() {
        List<MyApp.Pair> pairs = List.of(new MyApp.Pair(1, 7), new MyApp.Pair(3, 9), new MyApp.Pair(11, 16));
        System.out.println("pairs=" + pairs);

        List<MyApp.Pair> actual = app.merge(pairs);
        System.out.println("actual=" + actual);

        List<MyApp.Pair> expected =  List.of(new MyApp.Pair(1, 9), new MyApp.Pair(11, 16));

        assertEquals(expected, actual);
    }

    @Test
    public void testSample3() {
        List<MyApp.Pair> pairs = List.of(new MyApp.Pair(1, 7), new MyApp.Pair(3, 5));
        System.out.println("pairs=" + pairs);

        List<MyApp.Pair> actual = app.merge(pairs);
        System.out.println("actual=" + actual);

        List<MyApp.Pair> expected =  List.of(new MyApp.Pair(1, 7));

        assertEquals(expected, actual);
    }

    @Test
    public void testSample4() {
        List<MyApp.Pair> pairs = List.of(new MyApp.Pair(1, 7));
        System.out.println("pairs=" + pairs);

        List<MyApp.Pair> actual = app.merge(pairs);
        System.out.println("actual=" + actual);

        List<MyApp.Pair> expected =  List.of(new MyApp.Pair(1, 7));

        assertEquals(expected, actual);
    }

    @Test
    public void testSample5() {
        List<MyApp.Pair> pairs = List.of(new MyApp.Pair(1, 7), new MyApp.Pair(3, 9), new MyApp.Pair(7, 10), new MyApp.Pair(10, 14));
        System.out.println("pairs=" + pairs);

        List<MyApp.Pair> actual = app.merge(pairs);
        System.out.println("actual=" + actual);

        List<MyApp.Pair> expected =  List.of(new MyApp.Pair(1, 14));

        assertEquals(expected, actual);
    }
}

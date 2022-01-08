package com.balionis.java0;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/** */
public class MyAppTest {

    @Test
    public void testMe() {
        MyApp app = new MyApp();

        String expected = "myMsg";
        String actual = app.solution(List.of("myMsg"));

        assertEquals(expected, actual);
    }
}

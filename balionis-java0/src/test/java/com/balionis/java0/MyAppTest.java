package com.balionis.java0;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 */
public class MyAppTest {

    @Test
    public void testMe() {
        MyApp app = new MyApp();

        List<Long> values = Arrays.asList(10L, 1L, 103L, 0L);
        Long expected = 103L;
        Long actual = app.maxValue(values);

        assertEquals(expected, actual);
    }
}

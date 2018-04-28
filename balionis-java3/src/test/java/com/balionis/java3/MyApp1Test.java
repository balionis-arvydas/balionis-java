package com.balionis.java3;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 */
public class MyApp1Test {

    @Test
    public void testMe() {
        MyApp1 app = new MyApp1("myApp");

        app.run();

        assertTrue(true);
    }
}

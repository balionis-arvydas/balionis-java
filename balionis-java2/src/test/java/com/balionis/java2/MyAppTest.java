package com.balionis.java2;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 */
public class MyAppTest {

    @Test
    public void testMe1() {
        MyApp app = new MyApp("myApp", false);

        String expected = "myApp:myMsg:fun1";
        String actual = app.echo("myMsg");

        assertEquals(expected, actual);
    }

    @Test
    public void testMe2() {
        MyApp app = new MyApp("myApp", true);

        String expected = "myApp:myMsg:fun2";
        String actual = app.echo("myMsg");

        assertEquals(expected, actual);
    }
}

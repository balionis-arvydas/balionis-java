package com.balionis.javaGradle1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 */
public class MyAppTest {

    @Test
    public void testMe() {
        MyApp app = new MyApp("myApp");

        String expected = "myApp:myMsg";
        String actual = app.echo("myMsg");

        assertEquals(expected, actual);
    }
}

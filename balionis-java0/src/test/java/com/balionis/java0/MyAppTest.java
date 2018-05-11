package com.balionis.java0;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 */
public class MyAppTest {

    @Test
    public void testMe() {

        MyApp app = new MyApp();

        String expected = "echo:myMsg";
        String actual = app.echo("myMsg");

        assertEquals(expected, actual);
    }
}

package com.balionis.java1;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mockito;

/**
 */
public class MyAppTest {

    public static class MyServiceMock extends MyService {
        public MyServiceMock() {
            super("myApp");
        }
    }

    @Test
    public void testMe() {

        MyService service = new MyService("myApp");
        MyApp app = new MyApp("myApp", service);

        String expected = "myApp:myApp:myMsg";
        String actual = app.echo("myMsg");

        assertEquals(expected, actual);
    }

    @Test
    public void testMe2()  {
        // since we can't mock constructor with arguments we use "temp" mock class
        MyService serviceMock = Mockito.mock(MyServiceMock.class);

        Mockito.when(serviceMock.echo(Mockito.anyString())).thenReturn("myMsg1");

        MyApp app = new MyApp("myApp", serviceMock);

        String expected = "myApp:myMsg1";
        String actual = app.echo("myMsg");

        assertEquals(expected, actual);

        // test if called once
        Mockito.verify(serviceMock, Mockito.times(1)).echo("myMsg");
    }

    @Test
    public void testMe3()  {
        MyService service = new MyService("myService");
        MyService serviceSpy = Mockito.spy(service);

        Mockito.doReturn("myMsg1").when(serviceSpy).echo("myMsg");

        MyApp app = new MyApp("myApp", serviceSpy);

        String expected = "myApp:myMsg1";
        String actual = app.echo("myMsg");

        assertEquals(expected, actual);

        // test if called once
        Mockito.verify(serviceSpy, Mockito.times(1)).echo("myMsg");
    }
}

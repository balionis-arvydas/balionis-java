package com.balionis.spring5.standalone;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.ApplicationArguments;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 */
@RunWith(MockitoJUnitRunner.class)
public class MyAppTest {

    public static class MyApplicationArguments implements ApplicationArguments {
        public String[] getSourceArgs() {
            throw new UnsupportedOperationException();
        }

        public Set<String> getOptionNames() {
            throw new UnsupportedOperationException();
        }

        public boolean containsOption(String name) {
            throw new UnsupportedOperationException();
        }

        public List<String> getOptionValues(String name) {
            throw new UnsupportedOperationException();
        }

        public List<String> getNonOptionArgs() {
            throw new UnsupportedOperationException();
        }
    }

    @Mock
    MyService serviceMock;

    @InjectMocks
    MyApp app;

    @Test
    public void testMe() {

        Mockito.when(serviceMock.echo(Mockito.anyString())).thenReturn("myService:myMsg1");

        ApplicationArguments argumentsMock = Mockito.mock(MyApplicationArguments.class);

        Mockito.when(argumentsMock.getOptionValues("msg")).thenReturn(Arrays.asList("myMsg1"));

        app.run(argumentsMock);

        Mockito.verify(serviceMock, Mockito.times(1)).echo("myMsg1");

        // FIXME:  change this when 'run' returns something...
        assertTrue(true);
    }

}

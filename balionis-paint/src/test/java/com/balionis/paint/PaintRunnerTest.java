package com.balionis.paint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PaintRunnerTest.Config.class)
public class PaintRunnerTest {

    @MockBean
    private ActionReader readerMock;

    @MockBean
    private ConsoleWriter writerMock;

    @Autowired
    private PaintController controller;

    @Autowired
    private PaintRunner runner;

    @Test
    public void testMe() throws PaintException {
        Action prototype = new ActionForQuit();
        Action action = prototype.builder().withArguments(Arrays.asList("Q").toArray(new String[0])).withRunner(runner).build();

        Mockito.doReturn(action).when(readerMock).readNext(runner);

        Mockito.doNothing().when(writerMock).write(Mockito.anyString());
        Mockito.doNothing().when(writerMock).writeLine(Mockito.anyString());

        runner.run();

        Mockito.verify(readerMock, Mockito.times(1)).readNext(runner);
    }

    @Configuration
    static class Config {

        @Bean
        public PaintController getController() {
            return new PaintController();
        }

        @Bean
        public CanvasBuilder getCanvasBuilder() {
            return new CanvasBuilder();
        }

        @Bean
        public PaintRunner getRunner() {
            return new PaintRunnerImpl();
        }

    }
}

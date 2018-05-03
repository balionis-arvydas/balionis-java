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

    @Autowired
    private PaintController controller;

    @Autowired
    private PaintRunner runner;

    @Test
    public void testMe() throws PaintException {
        Action prototype = new QuitAction();
        Action action = prototype.builder().withArguments(Arrays.asList("Q").toArray(new String[0])).withRunner(runner).build();

        Mockito.doReturn(action).when(readerMock).readNext(runner);

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
        public PaintRunner getRunner() {
            return new PaintRunnerImpl();
        }
    }
}

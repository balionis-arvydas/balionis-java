package com.balionis.paint;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.balionis.paint.action.*;
import com.balionis.paint.console.*;

import java.util.Arrays;

/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PaintRunnerTest.Config.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PaintRunnerTest {

    @MockBean
    private ConsoleReader readerMock;

    @MockBean
    private ConsoleWriter writerMock;

    @Autowired
    private PaintController controller;

    @Autowired
    private PaintRunner runner;

    @Test
    public void testQuit() {

        Mockito.reset(readerMock);
        Mockito.when(readerMock.readLine()).thenReturn("Q");

        Mockito.doNothing().when(writerMock).write(Mockito.anyString());
        Mockito.doNothing().when(writerMock).writeLine(Mockito.anyString());

        runner.run();

        Mockito.verify(readerMock, Mockito.times(1)).readLine();
    }

    @Test
    public void testCreate() {

        Mockito.reset(readerMock);
        Mockito.when(readerMock.readLine())
                .thenReturn("C 6 4")
                .thenReturn("Q")
                .thenReturn("X");

        Mockito.doNothing().when(writerMock).write(Mockito.anyString());
        Mockito.doNothing().when(writerMock).writeLine(Mockito.anyString());

        String[] expect =  {
                "--------",
                "|      |",
                "|      |",
                "|      |",
                "|      |",
                "--------" };

        runner.run();

        String[] actual = controller.getCanvas().getLines().toArray(new String[0]);

        assertTrue(Arrays.equals(expect, actual));

        Mockito.verify(readerMock, Mockito.times(2)).readLine();
    }

    @Test
    public void testDraw() {

        Mockito.reset(readerMock);
        Mockito.when(readerMock.readLine())
                .thenReturn("C 20 5")
                .thenReturn("L 1 3 7 3")
                .thenReturn("L 7 1 7 3")
                .thenReturn("R 15 2 20 5")
                .thenReturn("Q")
                .thenReturn("X");

        Mockito.doNothing().when(writerMock).write(Mockito.anyString());
        Mockito.doNothing().when(writerMock).writeLine(Mockito.anyString());

        String[] expect =  {
                "----------------------",
                "|      x             |",
                "|      x       xxxxxx|",
                "|xxxxxxx       x    x|",
                "|              x    x|",
                "|              xxxxxx|",
                "----------------------" };

        runner.run();

        String[] actual = controller.getCanvas().getLines().toArray(new String[0]);

        assertTrue(Arrays.equals(expect, actual));

        Mockito.verify(readerMock, Mockito.times(5)).readLine();
    }

    @Test
    public void testNoCanvasForLine() {

        Mockito.reset(readerMock);
        Mockito.reset(writerMock);

        Mockito.when(readerMock.readLine())
                // .thenReturn("C 20 5")
                .thenReturn("L 1 3 7 3")
                .thenReturn("Q")
                .thenReturn("X");

        Mockito.doNothing().when(writerMock).write(Mockito.anyString());
        Mockito.doNothing().when(writerMock).writeLine(Mockito.anyString());

        runner.run();

        Mockito.verify(readerMock, Mockito.times(2)).readLine();

        Mockito.verify(writerMock, Mockito.times(1)).writeLine("error: canvas is not defined");
    }

    @Test
    public void testNoCanvasForRect() {

        Mockito.reset(readerMock);
        Mockito.reset(writerMock);

        Mockito.when(readerMock.readLine())
                // .thenReturn("C 20 5")
                .thenReturn("R 1 3 7 4")
                .thenReturn("Q")
                .thenReturn("X");

        Mockito.doNothing().when(writerMock).write(Mockito.anyString());
        Mockito.doNothing().when(writerMock).writeLine(Mockito.anyString());

        runner.run();

        Mockito.verify(readerMock, Mockito.times(2)).readLine();

        Mockito.verify(writerMock, Mockito.times(1)).writeLine("error: canvas is not defined");
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
        public ActionForCreate getActionForCreate() {
            return new ActionForCreate();
        }

        @Bean
        public ActionForLine getActionForLine() {
            return new ActionForLine();
        }

        @Bean
        public ActionForPaint getActionForPaint() {
            return new ActionForPaint();
        }

        @Bean
        public ActionForQuit getActionForQuit() {
            return new ActionForQuit();
        }

        @Bean
        public ActionForRect getActionForRect() {
            return new ActionForRect();
        }

        @Bean
        public ActionFactory getActionFactory() {
            return new ActionFactory();
        }

        @Bean
        public ActionReader getActionReader() {
            return new ActionReader();
        }

        @Bean
        public PaintRunner getRunner() {
            return new PaintRunner();
        }

    }
}

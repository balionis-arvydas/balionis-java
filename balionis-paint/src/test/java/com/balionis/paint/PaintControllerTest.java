package com.balionis.paint;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.mockito.Mockito;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PaintControllerTest.Config.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PaintControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(PaintControllerTest.class);

    public static class TestConsoleWriter extends ConsoleWriter {

        private List<String> lines = new ArrayList<>();
        private StringBuilder builder = new StringBuilder();

        public TestConsoleWriter() {
            super();
        }

        @Override
        public void writeLine(String line) {
            lines.add(builder.toString() + line);
            builder = new StringBuilder();
        }

        @Override
        public void write(String line) {
            builder.append(line);
        }

        public List<String> getLines() {
            return lines;
        }
    }

    @Autowired
    private ConsoleWriter writer;

    @Autowired
    private PaintController controller;

    @Test
    public void testPaint() throws ActionException {

        String[] args = {"C", "20", "5"};
        Action prototypeForCreate = new ActionForCreate();
        Action create = prototypeForCreate.builder().withArguments(args).build();

        create.handle(controller);

        String[] expect1 =  {
                "----------------------",
                "|                    |",
                "|                    |",
                "|                    |",
                "|                    |",
                "|                    |",
                "----------------------" };

        String[] actual1 = controller.getCanvas().getLines().toArray(new String[0]);
        logger.debug("testPaint: actual1={}", Arrays.toString(actual1));

        assertTrue(Arrays.equals(expect1, actual1));

        String[] expect2 =  {
                "", // always print blank line as first
                "----------------------",
                "|                    |",
                "|                    |",
                "|                    |",
                "|                    |",
                "|                    |",
                "----------------------" };
        String[] actual2 = ((TestConsoleWriter) writer).getLines().toArray(new String[0]);

        logger.debug("testPaint: actual2={}", Arrays.toString(actual2));
        assertTrue(Arrays.equals(expect2, actual2));

    }

    @Test (expected = IllegalStateException.class)
    public void testNoCanvasForLine() throws ActionException {

        String[] args = {"L", "1", "1", "1", "1"};
        Action prototype = new ActionForLine();
        Action action = prototype.builder().withArguments(args).build();

        action.handle(controller);

        assertTrue(false); // should never reach this place
    }

    @Test (expected = IllegalStateException.class)
    public void testNoCanvasForRect() throws ActionException {

        String[] args = {"R", "1", "1", "1", "1"};
        Action prototype = new ActionForLine();
        Action action = prototype.builder().withArguments(args).build();

        action.handle(controller);

        assertTrue(false); // should never reach this place
    }

    @Test (expected = IllegalStateException.class)
    public void testNoCanvasForUndo() throws ActionException {

        String[] args = {"U"};
        Action prototype = new ActionForUndo();
        Action action = prototype.builder().withArguments(args).build();

        action.handle(controller);

        assertTrue(false); // should never reach this place
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
        public CanvasHistory getCanvasHistory() {
            return new CanvasHistory();
        }

        @Bean
        public ConsoleWriter getConsoleWriter() {
            return new TestConsoleWriter();
        }

    }
}

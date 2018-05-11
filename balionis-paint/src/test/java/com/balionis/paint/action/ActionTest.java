package com.balionis.paint.action;

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
@ContextConfiguration(classes = ActionTest.Config.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ActionTest {

    private static final Logger logger = LoggerFactory.getLogger(ActionTest.class);

    @Autowired
    private ActionFactory actionFactory;

    @Test
    public void testCreate() throws ActionException {

        String[] args = {"C", "20", "5"};
        Action prototype = actionFactory.findAction(args[0]);
        ActionForCreate create = (ActionForCreate) prototype.builder().withArguments(args).build();

        assertEquals(20, create.getWidth());
        assertEquals(5, create.getHeight());
    }

    @Test (expected = ActionException.class)
    public void testCreateUsage() throws ActionException {

        String[] args = {"C", "20"};
        Action prototype = actionFactory.findAction(args[0]);
        ActionForCreate create = (ActionForCreate) prototype.builder().withArguments(args).build();

        assertNull(create); // should never reach this place
    }

    @Test
    public void testLine() throws ActionException {

        String[] args = {"L", "1", "7", "1", "14"};
        Action prototype = actionFactory.findAction(args[0]);
        ActionForLine line = (ActionForLine) prototype.builder().withArguments(args).build();

        assertEquals(1,  line.getX1());
        assertEquals(7,  line.getY1());
        assertEquals(1,  line.getX2());
        assertEquals(14, line.getY2());
    }

    @Test (expected = ActionException.class)
    public void testLineUsage() throws ActionException {

        String[] args = {"L", "1", "7"};
        Action prototype = actionFactory.findAction(args[0]);
        ActionForLine action = (ActionForLine) prototype.builder().withArguments(args).build();

        assertNull(action); // should never reach this place
    }

    @Test
    public void testRect() throws ActionException {

        String[] args = {"R", "1", "7", "1", "14"};
        Action prototype = actionFactory.findAction(args[0]);
        ActionForRect action = (ActionForRect) prototype.builder().withArguments(args).build();

        assertEquals(1,  action.getX1());
        assertEquals(7,  action.getY1());
        assertEquals(1,  action.getX2());
        assertEquals(14, action.getY2());
    }

    @Test (expected = ActionException.class)
    public void testRectUsage() throws ActionException {

        String[] args = {"R", "1", "7"};
        Action prototype = actionFactory.findAction(args[0]);
        ActionForRect action = (ActionForRect) prototype.builder().withArguments(args).build();

        assertNull(action); // should never reach this place
    }

    @Configuration
    static class Config {

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

    }
}

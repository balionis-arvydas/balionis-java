package com.balionis.maze;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class MazeTest {

    @Test
    public void testGood() {

        List<String> lines = Arrays.asList(
                "XXXXXX",
                "X    X",
                "XS   X",
                "X    X",
                "XXXFXX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        assertEquals(lines.size(), maze.getHeight());
        assertEquals(lines.get(0).length(), maze.getWidth());
        assertEquals(new Maze.Position(1, 2), maze.getStart());
        assertEquals(new Maze.Position(3, 4), maze.getFinish());
        assertEquals(11, maze.sum(Maze.Cell.SPACE));
        assertEquals(17, maze.sum(Maze.Cell.WALL));

        assertEquals(Maze.Cell.SPACE, maze.valueAt(new Maze.Position(1, 1)));
        assertEquals(Maze.Cell.SPACE, maze.valueAt(new Maze.Position(1, 1)));

        assertEquals(lines, maze.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {

        List<String> lines = new ArrayList<>();

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        assertEquals(lines.size(), maze.getHeight()); // never reach this place
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShortLine() {

        List<String> lines = Arrays.asList(
                "XXXXXX",
                "      ",
                "XS   X",
                "X    X",
                "XXXFXX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        assertEquals(lines.size(), maze.getHeight()); // never reach this place
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongLine() {

        List<String> lines = Arrays.asList(
                "XXXXXX",
                "X      X",
                "XS   X",
                "X    X",
                "XXXFXX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        assertEquals(lines.size(), maze.getHeight()); // never reach this place
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadChar() {

        List<String> lines = Arrays.asList(
                "XXXXXX",
                "X    X",
                "X?   X",
                "X    X",
                "XXXFXX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        assertEquals(lines.size(), maze.getHeight()); // never reach this place
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoStart() {

        List<String> lines = Arrays.asList(
                "XXXXXX",
                "X    X",
                "X    X",
                "X    X",
                "XXXFXX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        assertEquals(lines.size(), maze.getHeight()); // never reach this place
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoFinish() {

        List<String> lines = Arrays.asList(
                "XXXXXX",
                "X    X",
                "XS   X",
                "X    X",
                "XXXXXX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        assertEquals(lines.size(), maze.getHeight()); // never reach this place
    }
}

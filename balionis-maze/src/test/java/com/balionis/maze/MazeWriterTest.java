package com.balionis.maze;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 */
public class MazeWriterTest {

    @Test
    public void testMe() throws IOException {

        List<String> lines = Arrays.asList(
                "XXXXXX",
                "X    X",
                "XS   X",
                "X    X",
                "XXXFXX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        StringWriter buffer = new StringWriter();
        MazeWriter writer = new MazeWriter(new PrintWriter(buffer));
        writer.write(maze);

        String expect = lines.stream().reduce("", (aggr, line) -> aggr + line + System.lineSeparator());
        String actual = buffer.toString();

        assertEquals(expect, actual);
    }

}
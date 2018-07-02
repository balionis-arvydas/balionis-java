package com.balionis.maze;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;

/**
 */
public class MazeReaderTest {

    @Test
    public void testFile() throws IOException {
        MazeReader reader = new MazeReader("./src/main/resources/ExampleMaze.txt");
        Maze maze = reader.readMaze();

        assertEquals(15, maze.getHeight());
        assertEquals(15, maze.getWidth());
    }

    @Test
    public void testResource() throws IOException {
        MazeReader reader = new MazeReader("classpath:/ExampleMaze.txt");
        Maze maze = reader.readMaze();

        assertEquals(15, maze.getHeight());
        assertEquals(15, maze.getWidth());
    }

    @Test(expected = IOException.class)
    public void testNoResource() throws IOException {
        MazeReader reader = new MazeReader("classpath:ExampleMaze.txt");
        Maze maze = reader.readMaze();

        assertNotNull(maze); // never reach this place
    }
}
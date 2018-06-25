package com.balionis.maze;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;

/**
 */
public class MazeReaderTest {

    @Test
    public void testFile() throws IOException {
        MazeReader reader = new MazeReader();
        Maze maze = reader.readMaze("./src/main/resources/ExampleMaze.txt");

        assertEquals(15, maze.getHeight());
        assertEquals(15, maze.getWidth());
    }

    @Test
    public void testResource() throws IOException {
        MazeReader reader = new MazeReader();
        Maze maze = reader.readMaze("classpath:/ExampleMaze.txt");

        assertEquals(15, maze.getHeight());
        assertEquals(15, maze.getWidth());
    }

    @Test(expected = IOException.class)
    public void testNoResource() throws IOException {
        MazeReader reader = new MazeReader();
        Maze maze = reader.readMaze("classpath:ExampleMaze.txt");

        assertNotNull(maze); // never reach this place
    }
}
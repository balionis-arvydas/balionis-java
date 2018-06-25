package com.balionis.maze;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 */
public class ExplorerTest {

    @Test
    public void testBlankCorner() {

        List<String> lines = Arrays.asList(
                "XXXX",
                "XS X",
                "X  X",
                "XXFX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        Explorer explorer = new Explorer();
        List<Explorer.Move> moves = explorer.explore(maze);

        String expect = "[1,1,N][2,1,E][2,2,S][2,3,S]";
        String actual = moves.stream().map(x ->
            "[" + x.position.x + "," + x.position.y + "," + x.direction.value + "]"
        ).reduce("", (s, x) -> s + x);

        assertEquals(expect, actual);
    }

    @Test
    public void testBlankMiddle() {

        List<String> lines = Arrays.asList(
                "XXXXX",
                "X   X",
                "X S X",
                "X   X",
                "X   X",
                "XXXFX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        Explorer explorer = new Explorer();
        List<Explorer.Move> moves = explorer.explore(maze);

        String expect = "[2,2,N][3,2,E][3,3,S][2,3,W][1,3,W][1,2,N][1,1,N][2,1,E][3,1,E][3,1,W][2,1,W][1,1,S][1,2,S][1,3,E][1,4,S][2,4,E][3,4,E][3,5,S]";
        String actual = moves.stream().map(x ->
                "[" + x.position.x + "," + x.position.y + "," + x.direction.value + "]"
        ).reduce("", (s, x) -> s + x);

        assertEquals(expect, actual);
    }

    @Test
    public void testOneWall() {

        List<String> lines = Arrays.asList(
                "XXXXX",
                "XSX X",
                "X X X",
                "X   X",
                "X   X",
                "XXFXX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        Explorer explorer = new Explorer();
        List<Explorer.Move> moves = explorer.explore(maze);

        String expect = "[1,1,N][1,2,S][1,3,S][1,4,S][2,4,E][2,5,S]";
        String actual = moves.stream().map(x ->
                "[" + x.position.x + "," + x.position.y + "," + x.direction.value + "]"
        ).reduce("", (s, x) -> s + x);

        assertEquals(expect, actual);
    }

    @Test
    public void testTwoWalls() {

        List<String> lines = Arrays.asList(
                "XXXXXXX",
                "X X   X",
                "XSX X X",
                "X X X F",
                "X   X X",
                "XXXXXXX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        Explorer explorer = new Explorer();
        List<Explorer.Move> moves = explorer.explore(maze);

        String expect = "[1,2,N][1,1,N][1,1,S][1,2,S][1,3,S][1,4,S][2,4,E][3,4,E][3,3,N][3,2,N][3,1,N][4,1,E][5,1,E][5,2,S][5,3,S][6,3,E]";
        String actual = moves.stream().map(x ->
                "[" + x.position.x + "," + x.position.y + "," + x.direction.value + "]"
        ).reduce("", (s, x) -> s + x);

        assertEquals(expect, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void testNoExit() {

        List<String> lines = Arrays.asList(
                "XXXXXXX",
                "X X   X",
                "XSX X X",
                "X XXX F",
                "X   X X",
                "XXXXXXX");

        Maze.Builder builder = Maze.builder();
        Maze maze = builder.withLines(lines).build();

        Explorer explorer = new Explorer();
        List<Explorer.Move> moves = explorer.explore(maze);

        // should not reach this place
        String expect = "[1,2,N]";
        String actual = moves.stream().map(x ->
                "[" + x.position.x + "," + x.position.y + "," + x.direction.value + "]"
        ).reduce("", (s, x) -> s + x);

        assertEquals(expect, actual);
    }
}

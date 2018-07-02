package com.balionis.maze;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 */
public class AppTest {

    @Test
    public void testGood() throws IOException{

        String filename = "classpath:/ExampleMaze1.txt";

        StringWriter mazeBuffer = new StringWriter();
        MazeWriter mazeWriter = new MazeWriter(new PrintWriter(mazeBuffer));

        StringWriter journalBuffer = new StringWriter();
        ExplorerWriter explorerWriter = new ExplorerWriter(new PrintWriter(journalBuffer));

        App app = new App(new MazeReader(filename), mazeWriter, new Explorer(), explorerWriter);

        app.run();

        List<String> mazeLines = Arrays.asList(
            "XXXXX",
            "XSX X",
            "X X X",
            "X   X",
            "X   X",
            "XXFXX");

        String expectMaze = mazeLines.stream().reduce("", (aggr, line) -> aggr + line + System.lineSeparator());
        String actualMaze = mazeBuffer.toString();
        assertEquals(expectMaze, actualMaze);

        List<String> journalLines = Arrays.asList(
                "[1, 1, N]",
                "[1, 2, S]",
                "[1, 3, S]",
                "[1, 4, S]",
                "[2, 4, E]",
                "[2, 5, S]");

        String expectJournal = journalLines.stream().reduce("", (aggr, line) -> aggr + line + System.lineSeparator());
        String actualJournal = journalBuffer.toString();
        assertEquals(expectJournal, actualJournal);
    }

    @Test(expected = IllegalStateException.class)
    public void testNoExit() throws IOException{

        String filename = "classpath:/ExampleMaze2.txt";

        StringWriter mazeBuffer = new StringWriter();
        MazeWriter mazeWriter = new MazeWriter(new PrintWriter(mazeBuffer));

        StringWriter journalBuffer = new StringWriter();
        ExplorerWriter explorerWriter = new ExplorerWriter(new PrintWriter(journalBuffer));

        App app = new App(new MazeReader(filename), mazeWriter, new Explorer(), explorerWriter);

        app.run();

        // should not reach this place
        assertTrue(false);

    }

    @Test(expected = IOException.class)
    public void testNoFile() throws IOException{

        String filename = "classpath:/ExampleMaze3.txt";

        StringWriter mazeBuffer = new StringWriter();
        MazeWriter mazeWriter = new MazeWriter(new PrintWriter(mazeBuffer));

        StringWriter journalBuffer = new StringWriter();
        ExplorerWriter explorerWriter = new ExplorerWriter(new PrintWriter(journalBuffer));

        App app = new App(new MazeReader(filename), mazeWriter, new Explorer(), explorerWriter);

        app.run();

        // should not reach this place
        assertTrue(false);

    }
}

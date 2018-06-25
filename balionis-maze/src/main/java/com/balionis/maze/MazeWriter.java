package com.balionis.maze;

import java.io.PrintWriter;
import java.util.List;

public class MazeWriter {

    private PrintWriter writer;

    public MazeWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void write(Maze maze) {
        List<String> lines = maze.toList();
        lines.stream().forEach((line) -> {
            writer.println(line);
        });
		writer.flush();
    }
}

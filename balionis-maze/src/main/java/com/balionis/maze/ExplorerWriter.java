package com.balionis.maze;

import java.io.PrintWriter;
import java.util.List;

public class ExplorerWriter {

    private PrintWriter writer;

    public ExplorerWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void write(List<Explorer.Move> moves) {
        moves.stream().forEach((move) ->
            writer.println(
                    "[" + move.position.x
                    + ", " + move.position.y
                    + ", " + move.direction.value + "]")
        );
		writer.flush();
    }
}

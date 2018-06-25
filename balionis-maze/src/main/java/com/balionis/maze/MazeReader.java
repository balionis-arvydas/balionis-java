package com.balionis.maze;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class MazeReader {

    public Maze readMaze(String filename) throws IOException {
        List<String> lines;
        if (filename.startsWith("classpath:")) {
            try (InputStream resource = MazeReader.class.getResourceAsStream(filename.replaceFirst("classpath:", ""))) {
                if (resource == null) {
                    throw new IOException("cannot read resource {" + filename + "}.");
                }
                lines = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
            }
        } else {
            try (InputStream resource = new FileInputStream(filename)) {
                lines = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
            }
        }
        return Maze.builder().withLines(lines).build();
    }
}

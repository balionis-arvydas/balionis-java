package com.balionis.maze;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class);

    private String filename;

    private MazeReader      mazeReader;
    private MazeWriter      mazeWriter;
    private Explorer        explorer;
    private ExplorerWriter  explorerWriter;

    public App(String filename, MazeReader mazeReader, MazeWriter mazeWriter, Explorer explorer, ExplorerWriter explorerWriter) {
        this.filename = filename;
        this.mazeReader = mazeReader;
        this.mazeWriter = mazeWriter;
        this.explorer = explorer;
        this.explorerWriter = explorerWriter;
    }

    public void run() throws IOException {
        LOGGER.info("filename=" + filename);

        Maze maze = mazeReader.readMaze(filename);
        mazeWriter.write(maze);

        List<Explorer.Move> moves = explorer.explore(maze);
        explorerWriter.write(moves);

        LOGGER.info("done=" + filename);
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            usage();
            System.exit(1);
        }

        String filename = args[0];

        MazeWriter mazeWriter = new MazeWriter(new PrintWriter(System.out));
        ExplorerWriter explorerWriter = new ExplorerWriter(new PrintWriter(System.out));

        App app = new App(filename, new MazeReader(), mazeWriter, new Explorer(), explorerWriter);

        try {
            app.run();
        } catch (IOException exc) {
            LOGGER.error("cannot read filename {" + filename + "}", exc);
        }
		
    }

    public static void usage() {
        System.out.println("usage: {filename}");
    }
}

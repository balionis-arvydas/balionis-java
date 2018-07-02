package com.balionis.maze;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class);

    private MazeReader      mazeReader;
    private MazeWriter      mazeWriter;
    private Explorer        explorer;
    private ExplorerWriter  explorerWriter;

    public App(MazeReader mazeReader, MazeWriter mazeWriter, Explorer explorer, ExplorerWriter explorerWriter) {
        this.mazeReader = mazeReader;
        this.mazeWriter = mazeWriter;
        this.explorer = explorer;
        this.explorerWriter = explorerWriter;
    }

    public void run() throws IOException {

        Maze maze = mazeReader.readMaze();
        mazeWriter.write(maze);

        LOGGER.info("maze=" + maze);

        List<Explorer.Move> moves = explorer.explore(maze);
        explorerWriter.write(moves);

        LOGGER.info("moves=" + moves);
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            usage();
            System.exit(1);
        }

        String filename = args[0];

        MazeWriter mazeWriter = new MazeWriter(new PrintWriter(System.out));
        ExplorerWriter explorerWriter = new ExplorerWriter(new PrintWriter(System.out));

        App app = new App(new MazeReader(filename), mazeWriter, new Explorer(), explorerWriter);

        try {
            app.run();
        } catch (IOException exc) {
            String msg = "cannot read filename {" + filename + "}";
            System.out.println("error: " + msg);
            LOGGER.error(msg, exc);
            System.exit(1);
        }
		
    }

    public static void usage() {
        System.out.println("usage: {filename}");
    }
}

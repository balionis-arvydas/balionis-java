package com.balionis.maze;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Maze {

    public enum Cell {
        SPACE(' '), WALL('X'), START('S'), FINISH('F');

        public final char value;

        Cell(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static Cell valueOf(char value) {
            Cell[] vs = Cell.values();
            for (int i=0; i< vs.length; i++) {
                if (vs[i].value == value) {
                    return vs[i];
                }
            }
            throw new IllegalArgumentException("unsupported value {" + value + "}");
        }
    }

    public static class Position {
        public final int x;
        public final int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }

        @Override
        public boolean equals(Object other)  {
            if (other == null) {
                return false;
            }
            return equals((Position) other);
        }

        public boolean equals(Position other)  {
            if (other == null) {
                return false;
            }
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode()  {
            return y;
        }
    }

    public static class Builder {
        public List<String> lines = new ArrayList<>();

        public Builder withLines(List<String> lines) {
            this.lines = lines;
            return this;
        }

        public Maze build() {
            if (lines.isEmpty()) {
                throw new IllegalArgumentException("lines is empty");
            }
            String first = lines.get(0).trim();

            if (first.isEmpty()) {
                throw new IllegalArgumentException("first line is empty");
            }

            int width = first.length();
            int height = lines.size();
            Maze maze = new Maze(width, height);

            for (int y = 0; y < height; y++) {
                String line = lines.get(y).trim();
                if (line.length() != width) {
                    throw new IllegalArgumentException("line[" + y + "] is not " + width + " length.");
                }
                for (int x = 0; x < width; x++) {
                    char c = line.charAt(x);
                    if (c != Cell.WALL.value
                            && c != Cell.SPACE.value
                            && c != Cell.START.value
                            && c != Cell.FINISH.value) {
                        throw new IllegalArgumentException("cell[" + x + ", " + y + "] has unsupported character [" + c + "].");
                    }
                    maze.values[y][x] = c;
                }
            }

            Optional<Position> start = maze.find(Cell.START);
            if (!start.isPresent()) {
                throw new IllegalArgumentException("this maze has no start.");
            }

            Optional<Position> finish = maze.find(Cell.FINISH);
            if (!finish.isPresent()) {
                throw new IllegalArgumentException("this maze has no finish.");
            }

            maze.start = start.get();
            maze.finish = finish.get();

            return maze;
        }
    }

    private char[][] values;

    private final int width;
    private final int height;
    private Position start;  // cache to speed-up
    private Position finish; // cache to speed-up

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;

        this.values = new char[height][width];

        this.start =  new Position(-1,-1);
        this.finish =  new Position(-1,-1);
    }

    public Maze(Maze other) {
        this.width = other.width;
        this.height = other.height;

        this.values = new char[height][width];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                this.values[y][x] = other.values[y][x];
            }
        }

        this.start =  other.start;
        this.finish =  other.finish;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                buf.append(values[y][x]);
            }
            buf.append("\n");
        }
        return buf.toString();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Position getStart() {
        return start;
    }

    public Position getFinish() {
        return finish;
    }

    public Cell valueAt(int x, int y) {
        if (x < 0 || x >= width) {
            throw new IllegalArgumentException("width=" + width + ", x=" + x);
        }
        if (y < 0 || y >= height) {
            throw new IllegalArgumentException("height=" + height + ", y=" + y);
        }
        return Cell.valueOf(values[y][x]);
    }

    public Cell valueAt(Position pos) {
        return valueAt(pos.x, pos.y);
    }

    public int sum(Cell cell) {
        int res = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (values[y][x] == cell.value) {
                    res++;
                }
            }
        }
        return res;
    }

    public Optional<Position> find(Cell cell) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (values[y][x] == cell.value) {
                    return Optional.of(new Position(x, y));
                }
            }
        }
        return Optional.empty();
    }

    public List<String> toList() {
        List<String> lines = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            StringBuilder builder = new StringBuilder();
            for (int x = 0; x < width; x++) {
                builder.append(values[y][x]);
            }
            lines.add(builder.toString());
        }
        return lines;
    }

    public static Builder builder() {
        return new Builder();
    }
}

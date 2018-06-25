package com.balionis.maze;

import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Explorer {

    public enum Direction {
        N('N'), E('E'), S('S'), W('W');  // North, East, South, West

        public final char value;
        public final byte weight;  // how many quarters need from North to given
        public final byte dx;      // X coordinates change to move forward
        public final byte dy;      // Y coordinates change to move forward

        Direction(char value) {
            this.value = value;
            switch (value) {
                case 'N':
                    this.weight = 0;
                    this.dx = 0;
                    this.dy = -1;
                    break;
                case 'E':
                    this.weight = 1;
                    this.dx = 1;
                    this.dy = 0;
                    break;
                case 'S':
                    this.weight = 2;
                    this.dx = 0;
                    this.dy = 1;
                    break;
                default: // case 'W':
                    this.weight = 3;
                    this.dx = -1;
                    this.dy = 0;
                    break;
            }
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static Direction findNext(Direction direction, boolean clockwise) {
            if (clockwise) {
                switch (direction) {
                    case N: return Direction.E;
                    case E: return Direction.S;
                    case S: return Direction.W;
                    default: return Direction.N; // E
                }
            } else {
                switch (direction) {
                    case N: return Direction.W;
                    case W: return Direction.S;
                    case S: return Direction.E;
                    default: return Direction.N; // E
                }
            }
        }

        public static int findTurns(Direction left, Direction right) {
            return right.weight - left.weight;
        }
    }

    /**
     * keeps history step data
     */
    public static class Move {
        public final Maze.Position position;
        public final Direction direction;
        public final Maze.Cell cell;

        public Move(Maze.Position position, Direction direction, Maze.Cell cell) {
            this.position = position;
            this.direction = direction;
            this.cell = cell;
        }

        @Override
        public String toString() {
            return "[" + position.x
                    + ", " + position.y
                    + ", " + cell.value
                    + ", " + direction.value + "]";
        }
    }

    private static final Logger LOGGER = Logger.getLogger(Explorer.class);

    private Maze maze;

    private List<Move> path = new ArrayList<>();      // steps stack
    private List<Move> history = new ArrayList<>();   // steps full history

    private Maze.Position currentPosition;
    private Direction currentDirection;

    /**
     * @param maze
     * @return list of history moves taken from 'S' to 'F'
     * @throws IllegalArgumentException if unable to find 'F'
     */
    public List<Move> explore(Maze maze) {

        this.maze = maze;

        path = new ArrayList<>();
        history = new ArrayList<>();

        currentPosition = maze.getStart();
        currentDirection = Direction.N;
        Maze.Cell start = maze.valueAt(currentPosition);

        path.add(new Move(currentPosition, currentDirection, start));
        history.add(new Move(currentPosition, currentDirection, start));

        exploreMore();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("history=" + history);
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * computes single step and recursively calls evaluate next step
     */
    protected void exploreMore() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("currentPosition=" + currentPosition + ", currentDirection=" + currentDirection);
        }

        List<Move> available = peak();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("available=" + available);
        }

        Optional<Move> finish = available.stream().filter(move ->
            move.position.equals(maze.getFinish())
        ).findFirst();

        if (finish.isPresent()) {
            Move move = finish.get();
            turn(Direction.findTurns(currentDirection, move.direction));
            move();
        } else {
            Optional<Move> next = available.stream().filter(move ->
                !history.stream().filter(step -> step.position.equals(move.position)).findFirst().isPresent()
            ).findFirst();

            if (next.isPresent()) {
                Move move = next.get();
                turn(Direction.findTurns(currentDirection, move.direction));
                move();
                exploreMore();
            } else {
                pop();
                exploreMore();
            }
        }
    }

    /**
     * just move forward and nothing else
     */
    protected void move() {
        int x = currentPosition.x + currentDirection.dx;
        int y = currentPosition.y + currentDirection.dy;

        Maze.Cell cell = maze.valueAt(x, y);
        if (cell.equals(Maze.Cell.WALL) || cell.equals(Maze.Cell.START)) {
            throw new IllegalStateException("wall ahead [" + x + "," + y + "]");
        }
        currentPosition = new Maze.Position(x, y);

        path.add(new Move(currentPosition, currentDirection, cell));
        history.add(new Move(currentPosition, currentDirection, cell));
    }

    /**
     * change forward direction based on amount of 45' turns;
     * @param turns
     */
    protected void turn(int turns) {
        int n = Math.abs(turns);
        for (int i=0; i < n; i++) {
            currentDirection = Direction.findNext(currentDirection, turns >= 0);
        }
    }

    /**
     * find All available moves from current position. walls are ignored.
     */
    protected List<Move> peak() {

        List<Move> available = new ArrayList<>();

        int[] turns = { 1, 0, 3, 2 };
        for (int t = 0; t < turns.length; t++) {
            Move move = peak(turns[t]);
            if (!move.cell.equals(Maze.Cell.WALL)) {
                available.add(move);
            }
        }

        return available;

    }

    /**
     * find a next move (cell) based on the given 45' turns from current position.
     */
    protected Move peak(int turns) {

        Direction direction = currentDirection;
        int n = Math.abs(turns);
        for (int i=0; i < n; i++) {
            direction = Direction.findNext(direction, turns >= 0);
        }

        int x = currentPosition.x + direction.dx;
        int y = currentPosition.y + direction.dy;

        Maze.Cell cell = maze.valueAt(x, y);

        return new Move(new Maze.Position(x, y), direction, cell);
    }

    /**
     * remove last element from the 'stack' and restore them into current position and direction.
     */
    protected void pop() {

        if (path.isEmpty()) {
            throw new IllegalStateException("cannot find finish");
        }

        Move last = path.get(path.size() - 1);
        path.remove(path.size() - 1);

        currentDirection = Direction.findNext(last.direction, true);
        currentDirection = Direction.findNext(currentDirection, true);
        currentPosition = last.position;

        Maze.Cell cell = maze.valueAt(currentPosition);
        history.add(new Move(currentPosition, currentDirection, cell));

    }
}

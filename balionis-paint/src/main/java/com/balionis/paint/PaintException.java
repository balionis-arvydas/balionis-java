package com.balionis.paint;

public class PaintException extends Exception {

    private final String command;

    public PaintException(String command, String msg) {
        super(msg);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "{ msg=" + super.getMessage() + ", command=" + command + "}";
    }
}
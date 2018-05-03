package com.balionis.paint.action;

public class ActionException extends Exception {

    private final String command;

    public ActionException(String command, String msg) {
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
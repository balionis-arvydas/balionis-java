package com.balionis.paint.action;

public abstract class Action {

    private final String command;

    public interface ActionBuilder {
        ActionBuilder withArguments(String[] args);
        ActionBuilder withStopper(ActionStopper stopper);
        Action build() throws ActionException;
    }

    public Action(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "{ command=" + command + "}";
    }

    public String getCommand() {
        return command;
    }

    public abstract ActionBuilder builder();

    public abstract void handle(ActionVisitor visitor);

}


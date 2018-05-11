package com.balionis.paint.action;

public abstract class Action {

    private final String command;

    public static abstract class ActionBuilder {
        protected String[] args;
        protected ActionStopper stopper;
        public ActionBuilder withArguments(String[] args) {
            this.args = args;
            return this;
        }
        public ActionBuilder withStopper(ActionStopper stopper) {
            this.stopper = stopper;
            return this;
        }
        public abstract Action build() throws ActionException;
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


package com.balionis.paint.action;

import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ActionForCreate extends Action {

    public static final String COMMAND = "C";

    private int width;
    private int height;

    public static class ActionForCreateBuilder implements ActionBuilder {
        private String[] args;

        public ActionForCreateBuilder() {
        }
        public ActionBuilder withArguments(String[] args) {
            this.args = args;
            return this;
        }

        public ActionBuilder withStopper(ActionStopper stopper) {
            return this;
        }

        public Action build() throws ActionException {
            ActionForCreate action = new ActionForCreate();
            if (args.length < 3) {
                throw new ActionException(COMMAND, "usage: C <width> <height>");
            }
            try {
                action.width = Integer.parseInt(args[1]);
                if (action.width <= 0) {
                    throw new ActionException(COMMAND, "found width=" + action.width + " but <width> must be positive.");
                }
                action.height = Integer.parseInt(args[2]);
                if (action.height <= 0) {
                    throw new ActionException(COMMAND, "found height=" + action.height + " but <height> must be positive.");
                }
            } catch (NumberFormatException exc) {
                throw new ActionException(COMMAND, "both <width> and <height> must be integers.");
            }

            return action;
        }
    }

    public ActionForCreate() {
        super(COMMAND);
    }

    public ActionBuilder builder() {
        return new ActionForCreateBuilder();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void handle(ActionVisitor visitor) {
        visitor.handleAction(this);
    }
}


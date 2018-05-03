package com.balionis.paint;

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
        public ActionBuilder withRunner(PaintRunner runner) {
            return this;
        }
        public Action build() throws PaintException {
            ActionForCreate action = new ActionForCreate();
            if (args.length < 2) {
                throw new PaintException(COMMAND, "usage C <width> <height>");
            }
            try {
                action.width = Integer.parseInt(args[1]);
                if (action.width <= 0) {
                    throw new PaintException(COMMAND, "found width=" + action.width + " but <width> must be positive.");
                }
                action.height = Integer.parseInt(args[2]);
                if (action.height <= 0) {
                    throw new PaintException(COMMAND, "found height=" + action.height + " but <height> must be positive.");
                }
            } catch (NumberFormatException exc) {
                throw new PaintException(COMMAND, "both <width> and <height> must be integers.");
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


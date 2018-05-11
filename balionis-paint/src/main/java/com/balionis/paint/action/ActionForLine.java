package com.balionis.paint.action;

import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ActionForLine extends Action {

    public static final String COMMAND = "L";

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public static class ActionForLineBuilder implements ActionBuilder {
        private String[] args;

        public ActionForLineBuilder() {
        }
        public ActionBuilder withArguments(String[] args) {
            this.args = args;
            return this;
        }
        public ActionBuilder withStopper(ActionStopper stopper) {
            return this;
        }
        public Action build() throws ActionException {
            ActionForLine action = new ActionForLine();
            if (args.length < 5) {
                throw new ActionException(COMMAND, "usage: <x1> <y1> <x2> <y2>");
            }
            try {
                action.x1 = Integer.parseInt(args[1]);
                action.y1 = Integer.parseInt(args[2]);
                action.x2 = Integer.parseInt(args[3]);
                action.y2 = Integer.parseInt(args[4]);
                if (action.x1 > action.x2) {
                    throw new ActionException(COMMAND, "x1 cannot be greater than x2.");
                }

                if (action.y1 > action.y2) {
                    throw new ActionException(COMMAND, "y1 cannot be greater than y2.");
                }

                if (action.x1 != action.x2 && action.y1 != action.y2) {
                    throw new ActionException(COMMAND, "only horizontal or vertical lines are supported.");
                }
            } catch (NumberFormatException exc) {
                throw new ActionException(COMMAND, "<x1> <y1> <x2> <y2> must be integers.");
            }

            return action;
        }
    }

    public ActionForLine() {
        super(COMMAND);
    }

    @Override
    public String toString() {
        return "{ command=" + getCommand()
                + ", x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2
                + "}";
    }

    public ActionBuilder builder() {
        return new ActionForLineBuilder();
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public boolean isHorizontal() {
        return y1 == y2;
    }

    public void handle(ActionVisitor visitor) {
        visitor.handleAction(this);
    }
}


package com.balionis.paint;

import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ActionForRect extends Action {

    public static final String COMMAND = "R";

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public static class ActionForRectBuilder implements ActionBuilder {
        private String[] args;

        public ActionForRectBuilder() {
        }
        public ActionBuilder withArguments(String[] args) {
            this.args = args;
            return this;
        }
        public ActionBuilder withRunner(PaintRunner runner) {
            return this;
        }
        public Action build() throws PaintException {
            ActionForRect action = new ActionForRect();
            if (args.length < 4) {
                throw new PaintException(COMMAND, "usage: <x1> <y1> <x2> <y2>");
            }
            try {
                action.x1 = Integer.parseInt(args[1]);
                action.y1 = Integer.parseInt(args[2]);
                action.x2 = Integer.parseInt(args[3]);
                action.y2 = Integer.parseInt(args[4]);
                if (action.x1 > action.x2) {
                    throw new PaintException(COMMAND, "x1 cannot be greater than x2.");
                }

                if (action.y1 > action.y2) {
                    throw new PaintException(COMMAND, "y1 cannot be greater than y2.");
                }

            } catch (NumberFormatException exc) {
                throw new PaintException(COMMAND, "<x1> <y1> <x2> <y2> must be integers.");
            }

            return action;
        }
    }

    public ActionForRect() {
        super(COMMAND);
    }

    @Override
    public String toString() {
        return "{ command=" + getCommand()
                + ", x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2
                + "}";
    }

    public ActionBuilder builder() {
        return new ActionForRectBuilder();
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

    public void handle(ActionVisitor visitor) {
        visitor.handleAction(this);
    }
}


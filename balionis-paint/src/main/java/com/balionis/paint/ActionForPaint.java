package com.balionis.paint;

import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ActionForPaint extends Action {

    public static class ActionForPaintBuilder implements ActionBuilder {

        public ActionForPaintBuilder() {
        }
        public ActionBuilder withArguments(String[] args) {
            return this;
        }
        public ActionBuilder withRunner(PaintRunner runner) {
            return this;
        }
        public Action build() throws PaintException {
            return new ActionForPaint();
        }
    }

    public ActionForPaint() {
        super("P");
    }

    public ActionBuilder builder() {
        return new ActionForPaintBuilder();
    }

    public void handle(ActionVisitor visitor) {
        visitor.handleAction(this);
    }
}


package com.balionis.paint.action;

import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ActionForPaint extends Action {

    public static final String COMMAND = "P";

    public static class ActionForPaintBuilder extends ActionBuilder {

        @Override
        public Action build() throws ActionException {
            return new ActionForPaint();
        }
    }

    public ActionForPaint() {
        super(COMMAND);
    }

    public ActionBuilder builder() {
        return new ActionForPaintBuilder();
    }

    public void handle(ActionVisitor visitor) {
        visitor.handleAction(this);
    }
}


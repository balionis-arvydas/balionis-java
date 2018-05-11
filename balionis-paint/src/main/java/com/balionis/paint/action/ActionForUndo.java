package com.balionis.paint.action;

import org.springframework.stereotype.Component;

@Component
public class ActionForUndo extends Action {

    public static final String COMMAND = "U";

    public static class ActionForUndoBuilder extends ActionBuilder {

        @Override
        public Action build() throws ActionException {
            ActionForUndo action = new ActionForUndo();
            return action;
        }
    }

    public ActionForUndo() {
        super(COMMAND);
    }

    public ActionBuilder builder() {
        return new ActionForUndoBuilder();
    }

    public void handle(ActionVisitor visitor) {
        visitor.handleAction(this);
    }
}


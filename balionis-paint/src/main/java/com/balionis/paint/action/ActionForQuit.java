package com.balionis.paint.action;

import org.springframework.stereotype.Component;

@Component
public class ActionForQuit extends Action {

    public static final String COMMAND = "Q";

    private ActionStopper stopper;

    public static class ActionForQuitBuilder extends ActionBuilder {
        @Override
        public Action build() throws ActionException {
            ActionForQuit action = new ActionForQuit();
            action.stopper = this.stopper;
            return action;
        }
    }

    public ActionForQuit() {
        super(COMMAND);
    }

    public ActionStopper getStopper() {
        return stopper;
    }

    public ActionBuilder builder() {
        return new ActionForQuitBuilder();
    }

    public void handle(ActionVisitor visitor) {
        visitor.handleAction(this);
    }
}


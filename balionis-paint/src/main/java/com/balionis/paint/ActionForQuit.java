package com.balionis.paint;

import org.springframework.stereotype.Component;

@Component
public class ActionForQuit extends Action {

    public static final String COMMAND = "Q";

    private PaintRunner runner;

    public static class ActionForQuitBuilder implements ActionBuilder {
        private PaintRunner runner;

        public ActionForQuitBuilder() {
        }
        public ActionBuilder withArguments(String[] args) {
            return this;
        }
        public ActionBuilder withRunner(PaintRunner runner) {
            this.runner = runner;
            return this;
        }
        public Action build() throws PaintException {
            ActionForQuit action = new ActionForQuit();
            action.runner = this.runner;
            return action;
        }
    }

    public ActionForQuit() {
        super(COMMAND);
    }

    public PaintRunner getRunner() {
        return runner;
    }

    public ActionBuilder builder() {
        return new ActionForQuitBuilder();
    }

    public void handle(ActionVisitor visitor) {
        visitor.handleAction(this);
    }
}


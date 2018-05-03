package com.balionis.paint;

import org.springframework.stereotype.Component;

@Component
public class QuitAction extends Action {

    private PaintRunner runner;

    public static class QuitBuilder implements ActionBuilder {
        private PaintRunner runner;

        public QuitBuilder() {
        }
        public ActionBuilder withArguments(String[] args) {
            return this;
        }
        public ActionBuilder withRunner(PaintRunner runner) {
            this.runner = runner;
            return this;
        }
        public Action build() throws PaintException {
            QuitAction action = new QuitAction();
            action.runner = this.runner;
            return action;
        }
    }

    public QuitAction() {
        super("Q");
    }

    public PaintRunner getRunner() {
        return runner;
    }

    public ActionBuilder builder() {
        return new QuitBuilder();
    }

    public void handle(ActionVisitor visitor) {
        visitor.handleAction(this);
    }
}


package com.balionis.paint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaintController implements ActionVisitor {

    private static final Logger logger = LoggerFactory.getLogger(PaintController.class);

    public void handleAction(QuitAction action) {
        logger.debug("handleAction: action={}", action);
        action.getRunner().stop();
    }
}


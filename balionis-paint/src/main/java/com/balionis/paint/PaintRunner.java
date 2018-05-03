package com.balionis.paint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.balionis.paint.action.Action;
import com.balionis.paint.action.ActionStopper;
import com.balionis.paint.action.ActionReader;

@Component
public class PaintRunner implements Runnable, ActionStopper {

    private static final Logger logger = LoggerFactory.getLogger(PaintRunner.class);

    private volatile boolean alive = true;

    @Autowired
    private ActionReader reader;

    @Autowired
    private PaintController controller;

    @Override
    public void run() {

        while(alive) {
            Action action = reader.readNext(this);
            logger.info("run: action={}", action);

            try {
                action.handle(controller);
            } catch (Exception exc) {
                controller.handleException(exc);
            }
        }

        logger.info("run: done");
    }

    @Override
    public void stop() {
        alive = false;
    }
}


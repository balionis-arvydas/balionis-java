package com.balionis.paint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaintRunner implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(PaintRunner.class);

    @Override
    public void run() {
        logger.info("run: done");
    }
}


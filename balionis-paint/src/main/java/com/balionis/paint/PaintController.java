package com.balionis.paint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaintController implements ActionVisitor {

    private static final Logger logger = LoggerFactory.getLogger(PaintController.class);

    @Autowired
    private CanvasBuilder canvasBuilder;

    @Autowired
    private ConsoleWriter writer;

    private Canvas canvas;

    public Canvas getCanvas() {
        return canvas;
    }

    public void handleAction(ActionForQuit action) {
        logger.debug("handleAction: action={}", action);
        action.getRunner().stop();
    }

    public void handleAction(ActionForCreate action) {
        logger.debug("handleAction: action={}", action);

        canvas = canvasBuilder.withDimentions(action.getWidth(), action.getHeight()).build();

        logger.debug("handleAction: canvas={}", canvas);

        handleAction(new ActionForPaint());
    }

    public void handleAction(ActionForPaint action) {
        logger.debug("handleAction: action={}", action);

        if (canvas == null) {
            throw new IllegalStateException("canvas is not defined");
        }
        List<String> lines = canvas.getLines();
        writer.writeLine();
        for (String line : lines) {
            writer.writeLine(line);
        }
    }

    public void handleException(Exception exc) {
        logger.debug("handleException: msg={}", exc.getMessage(), exc);

        writer.writeLine();
        writer.writeLine("error: " + exc.getMessage());
    }

}
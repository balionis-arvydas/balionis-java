package com.balionis.paint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import com.balionis.paint.action.*;
import com.balionis.paint.console.*;

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
        action.getStopper().stop();
    }

    public void handleAction(ActionForCreate action) {
        logger.debug("handleAction: action={}", action);

        canvas = canvasBuilder.withDimensions(action.getWidth(), action.getHeight()).build();

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

    public void handleAction(ActionForLine action) {
        logger.debug("handleAction: action={}", action);

        if (canvas == null) {
            throw new IllegalStateException("canvas is not defined");
        }

        if (action.isHorizontal()) {
            for (int x = action.getX1(); x <= action.getX2(); x++) {
                canvas.replace(x, action.getY1(), 'x');
            }
        } else {
            for (int y = action.getY1(); y <= action.getY2(); y++) {
                canvas.replace(action.getX1(), y, 'x');
            }
        }

        logger.debug("handleAction: canvas={}", canvas);

        handleAction(new ActionForPaint());
    }

    public void handleAction(ActionForRect action) {
        logger.debug("handleAction: action={}", action);

        if (canvas == null) {
            throw new IllegalStateException("canvas is not defined");
        }

        for (int x = action.getX1(); x <= action.getX2(); x++) {
            canvas.replace(x, action.getY1(), 'x');
        }

        for (int y = action.getY1(); y <= action.getY2(); y++) {
            canvas.replace(action.getX1(), y, 'x');
            canvas.replace(action.getX2(), y, 'x');
        }

        for (int x = action.getX1(); x <= action.getX2(); x++) {
            canvas.replace(x, action.getY2(), 'x');
        }

        logger.debug("handleAction: canvas={}", canvas);

        handleAction(new ActionForPaint());
    }

    public void handleException(Exception exc) {
        logger.debug("handleException: msg={}", exc.getMessage(), exc);

        writer.writeLine();
        writer.writeLine("error: " + exc.getMessage());
    }

}
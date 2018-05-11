package com.balionis.paint.action;

public interface ActionVisitor {

    void handleAction(ActionForQuit action);

    void handleAction(ActionForCreate action);

    void handleAction(ActionForPaint action);

    void handleAction(ActionForLine action);

    void handleAction(ActionForRect action);

    void handleAction(ActionForUndo action);
}


package com.balionis.paint;

public interface ActionVisitor {

    void handleAction(ActionForQuit action);

    void handleAction(ActionForCreate action);

    void handleAction(ActionForPaint action);
}


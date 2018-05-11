package com.balionis.paint;

import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Optional;

@Component
public class CanvasHistory {
    private final Deque<Canvas> stack;

    public CanvasHistory() {
        this(new ArrayDeque<>());
    }

    public CanvasHistory(Deque<Canvas> stack) {
        this.stack = stack;
    }

    public void clear() {
        stack.clear();
    }

    public void push(Canvas canvas) {
        stack.push(canvas);
    }

    public Optional<Canvas> pop() {
        if (stack.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(stack.pop());
    }
}

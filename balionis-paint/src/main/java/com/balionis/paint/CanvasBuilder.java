package com.balionis.paint;

import org.springframework.stereotype.Component;

@Component
public class CanvasBuilder {

    private int width;
	private int height;
	
    public CanvasBuilder withDimentions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Canvas build() {
        return new Canvas(width, height);
    }
}


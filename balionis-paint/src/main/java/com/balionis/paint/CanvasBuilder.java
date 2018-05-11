package com.balionis.paint;

import org.springframework.stereotype.Component;

@Component
public class CanvasBuilder {

    private int width;
	private int height;
	
    public CanvasBuilder withDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Canvas build() {
        return new CanvasDecorator(new CanvasHolder(width, height));
    }
}


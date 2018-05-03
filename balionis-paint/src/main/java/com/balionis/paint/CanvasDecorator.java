package com.balionis.paint;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CanvasDecorator extends Canvas {

    public CanvasDecorator(int width, int height) {
		super(width, height);
    }

    public List<String> getLines() {
		StringBuilder border = new StringBuilder();
        for (int x = 0; x < super.getWidth() + 2; x++) {
            border.append("-");
        }

        List<String> lines = super.getLines().stream().map(x -> "|" + x + "|").collect(Collectors.toList());

        lines.add(0, border.toString());
        lines.add(border.toString());

        return lines;
	}

}


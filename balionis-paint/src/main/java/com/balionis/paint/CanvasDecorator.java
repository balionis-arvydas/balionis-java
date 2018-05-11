package com.balionis.paint;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CanvasDecorator implements Canvas {

    private CanvasHolder holder;

    public CanvasDecorator(CanvasHolder holder) {
		this.holder = holder;
    }

    public void replace(int x, int y, char c) {
        holder.replace(x, y, c);
    }

    public List<String> getLines() {
		StringBuilder border = new StringBuilder();
        for (int x = 0; x < holder.getWidth() + 2; x++) {
            border.append("-");
        }

        List<String> lines = holder.getLines().stream().map(x -> "|" + x + "|").collect(Collectors.toList());

        lines.add(0, border.toString());
        lines.add(border.toString());

        return lines;
	}

    public Canvas clone() {
        return new CanvasDecorator((CanvasHolder) holder.clone());
    }
}


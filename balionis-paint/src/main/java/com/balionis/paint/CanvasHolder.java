package com.balionis.paint;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CanvasHolder implements Canvas {

    private int width;
    private int height;

    private List<StringBuilder> lines = new ArrayList<>();

    public CanvasHolder(int width, int height) {
        this.width = width;
        this.height = height;

        for (int y = 0; y < height; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < width; x++) {
                line.append(" ");
            }
            lines.add(line);
        }
    }

    @Override
    public String toString() {
        return "{ width=" + width + ", height=" + height
                + ", lines=" + lines.stream().map(x -> "{" + x.toString() + "}").collect(Collectors.joining(", "))
                + "}";
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void replace(int x, int y, char c) {
        if (x > 0 && x <= width && y > 0 && y <= height) {
            lines.get(y - 1).setCharAt(x - 1, c);
        }
    }

    public List<String> getLines() {
        return lines.stream().map(x -> x.toString()).collect(Collectors.toList());
    }

    public Object clone() {
        CanvasHolder obj = new CanvasHolder(width, height);
        for (int i = 0; i < height; i++) {
            obj.lines.get(i).replace(0, width, lines.get(i).toString());
        }
        return obj;
    }
}


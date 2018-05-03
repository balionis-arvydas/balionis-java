package com.balionis.paint;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Canvas {

    private int width;
	private int height;
	
	private List<StringBuilder> lines = new ArrayList<>();

    public Canvas(int width, int height) {
		this.width = width;
		this.height = height;
		
		for (int y = 0; y < height + 2; y++) {
			StringBuilder line = new StringBuilder();
			line.append("|");
            for (int x = 1; x < width + 1; x++) {
                line.append(" ");
            }
            line.append("|");
			lines.add(line);
		}

		if (lines.size() > 0) {
		    StringBuilder first = lines.get(0);
            StringBuilder last = lines.get(lines.size() - 1);

            for (int x = 0; x < width + 2; x++) {
                first.setCharAt(x, '-');
                last.setCharAt(x, '-');
            }
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
		if (x >= 0 && x < width - 1 && y >=0 && y < height - 1) {
			lines.get(y + 1).setCharAt(x + 1, c);
		} 
	}
	
    public List<String> getLines() {
		return lines.stream().map(x -> x.toString()).collect(Collectors.toList());
	}

}


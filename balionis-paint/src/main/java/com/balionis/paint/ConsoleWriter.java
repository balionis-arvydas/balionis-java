package com.balionis.paint;

import org.springframework.stereotype.Component;

import java.io.PrintStream;

@Component
public class ConsoleWriter {

    private final PrintStream out;

    public ConsoleWriter() {
        this(System.out);
    }

    public ConsoleWriter(PrintStream out) {
        this.out = out;
    }

    public void writeLine() {
        writeLine("");
    }

    public void writeLine(String line) {
        out.println(line);
    }

    public void write(String line) {
        out.print(line);
    }
}


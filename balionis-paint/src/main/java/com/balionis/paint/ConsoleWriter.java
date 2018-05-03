package com.balionis.paint;

import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
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


package com.balionis.paint;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class ConsoleReader {

    private final Scanner source;

    public ConsoleReader() {
        this(System.in);
    }

    public ConsoleReader(InputStream in) {
        this.source = new Scanner(in);
    }

    public String readLine() {
        return source.nextLine();
    }
}


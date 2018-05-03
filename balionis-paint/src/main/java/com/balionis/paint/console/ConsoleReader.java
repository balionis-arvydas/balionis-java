package com.balionis.paint.console;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Scanner;

@Service
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


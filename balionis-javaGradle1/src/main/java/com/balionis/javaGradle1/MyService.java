package com.balionis.javaGradle1;

public class MyService {
    private final String name;

    public MyService(String name) {
        this.name = name;
    }

    public String echo(String msg) {
        return name + ":" + msg;
    }
}

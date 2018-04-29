package com.balionis.spring5.restful;

public class MyMessage {

    private final long id;
    private final String content;

    public MyMessage(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
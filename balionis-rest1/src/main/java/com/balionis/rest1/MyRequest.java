package com.balionis.rest1;

public class MyRequest<T> {
    private T payload;

    public MyRequest(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return this.payload;
    }

    public String toString() {
        return "{ payload=" + payload + "}";
    }
}

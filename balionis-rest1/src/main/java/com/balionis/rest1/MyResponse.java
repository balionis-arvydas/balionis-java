package com.balionis.rest1;

public class MyResponse<T> {

    public static final int SUCCESS = 0;

    private int status;
    private String reason;

    private T payload;

    public MyResponse(T payload) {
        this.payload = payload;
        this.status = SUCCESS;
    }

    public MyResponse(int status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public T getPayload() {
        return this.payload;
    }

    public String toString() {
        return "{ status=" + status
                + ", payload=" + payload
                + ", reason=" + reason
                + "}";
    }
}

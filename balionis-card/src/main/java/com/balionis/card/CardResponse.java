package com.balionis.card;

public class CardResponse extends CardMessage {

    public enum Status {
        SUCCESS,
        FAILURE
    }

    private final double balance;
    private final Status status;
    private final String reason;

    public CardResponse(String cardName, double balance, Status status, String reason) {
        super(cardName);
        this.balance = balance;
        this.status = status;
        this.reason = reason;
    }

    public double getBalance() {
        return balance;
    }

    public Status getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "{ super=" + super.toString() + ", balance=" + balance + ", status=" + status + ", reason=" + reason + "}";
    }
}
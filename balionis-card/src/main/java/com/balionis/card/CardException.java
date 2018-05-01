package com.balionis.card;

public class CardException extends Exception {

    private final String cardName;
    private final double balance;

    public CardException(String cardName, double balance, String msg) {
        super(msg);
        this.cardName = cardName;
        this.balance = balance;
    }

    public String getCardName() {
        return cardName;
    }
    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "{ msg=" + super.getMessage() + ", cardName=" + cardName + ", balance=" + balance + "}";
    }
}
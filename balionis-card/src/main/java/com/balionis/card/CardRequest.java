package com.balionis.card;

public class CardRequest extends CardMessage {

    private final double amount;

    public CardRequest(String cardName, double amount) {
        super(cardName);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "{ super=" + super.toString() + ", amount=" + amount + "}";
    }

    @Override
    public boolean equals(Object other) {
        return equals((CardRequest) other);
    }

    public boolean equals(CardRequest other) {
        return this.getCardName().equals(other.getCardName())
               && this.amount == other.amount;
    }
}
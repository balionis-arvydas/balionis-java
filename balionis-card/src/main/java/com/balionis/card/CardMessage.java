package com.balionis.card;

public class CardMessage {

    private final String cardName;

    public CardMessage(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }

    @Override
    public String toString() {
        return "{ cardName=" + cardName + "}";
    }
}
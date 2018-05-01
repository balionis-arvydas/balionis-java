package com.balionis.card.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.DoubleAccumulator;

import com.balionis.card.CardRequest;
import com.balionis.card.CardException;

@Repository
public class CardRepository {

    @Autowired
    CardRepositoryConfig config;

    public double update(CardRequest req) throws CardException {

        DoubleAccumulator accumulator = config.getCards().get(req.getCardName());
        if (accumulator == null) {
            throw new CardException(req.getCardName(), -1, "unknown cardName");
        }

        synchronized (accumulator) {
            if (accumulator.get() + req.getAmount() < 0) {
                throw new CardException(req.getCardName(), accumulator.get(), "not enough funds");
            }
            accumulator.accumulate(req.getAmount());

            return accumulator.get();
        }
    }
}

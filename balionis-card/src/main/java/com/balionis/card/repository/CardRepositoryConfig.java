package com.balionis.card.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.DoubleAccumulator;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardRepositoryConfig {

    private static final Logger logger = LoggerFactory.getLogger(CardRepositoryConfig.class);

    private Map<String, DoubleAccumulator> cards = new HashMap<>();

    @Value("#{'${cardNames}'.split(',')}")
    private List<String> cardNames;

    @Value("#{'${cardBalances}'.split(',')}")
    private List<Double> cardBalances;

    public Map<String, DoubleAccumulator> getCards() {
        return cards;
    }

    @PostConstruct
    public void init() {

        logger.debug("init: cardNames={}, cardBalances={}", cardNames, cardBalances);

        if (cardNames.size() != cardBalances.size()) {
            throw new IllegalStateException("cardNames.size != cardBalances.size");
        }
        for (int i = 0; i < cardNames.size(); i++) {
            if (cardNames.get(i).isEmpty()) {
                continue;
            }
            cards.put(cardNames.get(i), new DoubleAccumulator((x, y) -> x + y, cardBalances.get(i).doubleValue()));
        }
        if (cards.isEmpty()) {
            throw new IllegalStateException("cards is empty");
        }
    }
}

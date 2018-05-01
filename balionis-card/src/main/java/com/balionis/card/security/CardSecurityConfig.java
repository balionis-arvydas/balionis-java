package com.balionis.card.security;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardSecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(CardSecurityConfig.class);

    private Map<String, String> cards = new HashMap<>();

    @Value("#{'${cardNames}'.split(',')}")
    private List<String> cardNames;

    @Value("#{'${cardPins}'.split(',')}")
    private List<String> cardPins;

    @Bean
    public Map<String, String> getCards() {
        return cards;
    }

    @PostConstruct
    public void init() {

        logger.debug("init: cardNames={}, cardPins.size={}", cardNames, cardPins.size());

        if (cardNames.size() != cardPins.size()) {
            throw new IllegalStateException("cardNames.size != cardPins.size");
        }

        for (int i = 0; i < cardNames.size(); i++) {
            if (cardNames.get(i).isEmpty()) {
                continue;
            }
            cards.put(cardNames.get(i), cardPins.get(i));
        }

        if (cards.isEmpty()) {
            throw new IllegalStateException("cards is empty");
        }
    }
}

package com.balionis.card.repository;

import com.balionis.card.CardException;
import com.balionis.card.CardRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CardRepositoryTest.Config.class)
public class CardRepositoryTest {

    @Autowired
    private CardRepositoryConfig config;

    @Autowired
    private CardRepository repository;

    @Test
    public void testWithdraw() throws CardException {

        CardRequest req = new CardRequest("card1", -1.0);

        double expect = 9.0;
        double actual = repository.update(req);
        assertEquals(expect, actual,  0.01);
    }

    @Test
    public void testTopUp() throws CardException {

        CardRequest req = new CardRequest("card2", 10.0);

        double expect = 110.0;
        double actual = repository.update(req);
        assertEquals(expect, actual,  0.01);
    }

    @Test(expected = CardException.class)
    public void testNoFunds() throws CardException {

        CardRequest req = new CardRequest("card1", -100.0);

        double actual = repository.update(req);

        assertEquals(0.0, actual,  0.01); // should never reach
    }

    @Test(expected = CardException.class)
    public void testBadCard() throws CardException {

        CardRequest req = new CardRequest("card9", 100.0);

        double actual = repository.update(req);

        assertEquals(0.0, actual,  0.01); // should never reach
    }

    @Configuration
    static class Config {

        @Bean
        public static PropertySourcesPlaceholderConfigurer properties() {
            final PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
            Properties properties = new Properties();

            properties.setProperty("cardNames", "card1,card2,card3");
            properties.setProperty("cardBalances", "10.0, 100.0, 1000.0");

            cfg.setProperties(properties);
            return cfg;
        }

        @Bean
        public CardRepositoryConfig getConfig() {
            return new CardRepositoryConfig();
        }

        @Bean
        public CardRepository getRepository() {
            return new CardRepository();
        }

    }
}

package com.balionis.spring5.restful;

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
@ContextConfiguration(classes = MyServiceTest.Config.class)
public class MyServiceTest {

    @Autowired
    private MyService service;

    @Test
    public void testMe() {
        String expect = "testService@testEnv:testMsg";
        String actual = service.echo("testMsg");
        assertEquals(expect, actual);
    }

    @Configuration
    static class Config {

        @Bean
        public static PropertySourcesPlaceholderConfigurer properties() {
            final PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
            Properties properties = new Properties();

            properties.setProperty("service.env", "testEnv");
            properties.setProperty("service.name", "testService");

            cfg.setProperties(properties);
            return cfg;
        }

        @Bean
        public MyService getService() {
            return new MyService();
        }

    }
}

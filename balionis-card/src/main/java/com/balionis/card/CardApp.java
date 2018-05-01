package com.balionis.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class CardApp {

    private static final Logger logger = LoggerFactory.getLogger(CardApp.class);

    public static void main(String[] args) {
        logger.debug("main: args={}", Arrays.toString(args));

        SpringApplication.run(CardApp.class, args);
    }
}


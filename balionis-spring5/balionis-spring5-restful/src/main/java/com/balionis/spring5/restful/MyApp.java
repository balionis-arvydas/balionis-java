package com.balionis.spring5.restful;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class MyApp {

    private static final Log logger = LogFactory.getLog(MyApp.class);

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}


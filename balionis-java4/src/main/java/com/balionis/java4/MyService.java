package com.balionis.java4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyService {

    private static final Logger  LOGGER = LoggerFactory.getLogger(MyService.class);

    private final String name;

    public MyService(String name) {
        this.name = name;
    }

    public String echo(String msg) {

        LOGGER.info("echo: msg={}", msg);

        String res = name + ":" + msg;

        LOGGER.info("echo: res{}", res);

        return res;
    }
}

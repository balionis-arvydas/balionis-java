package com.balionis.spring5.restful;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class MyService {

    @Value("${service.env}")
    private String env;

    @Value("${service.name}")
    private String name;

    public String echo(String msg) {
        return name + "@" + env + ":" + msg;
    }
}

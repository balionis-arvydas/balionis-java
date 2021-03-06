package com.balionis.spring5.restful;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class MyController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    MyService service;

    @Secured("ROLE_USER")
    @PreAuthorize("hasPermission(#message, 'place-message')")
    @RequestMapping(path = "/echo", method = RequestMethod.GET)
    public MyMessage echo(@RequestParam(value="message", defaultValue="Somebody") String message) {
        return new MyMessage(counter.incrementAndGet(), service.echo(message));
    }

}
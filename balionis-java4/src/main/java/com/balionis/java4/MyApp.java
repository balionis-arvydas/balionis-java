package com.balionis.java4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyApp {

    private static final Logger  LOGGER = LoggerFactory.getLogger(MyApp.class);
	
	private final String name;
    private final MyService service;

    public MyApp(String name, MyService service) {
        this.name = name;
        this.service = service;
    }
	
    public String echo(String msg) {
        LOGGER.debug("echo: name={}, msg={}", name, msg);
        return name + ":" + service.echo(msg);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            usage();
            System.exit(2);
        }

        String name = args[0];
		String msg = args[1];

        MyService service = new MyService(name);
        MyApp app = new MyApp(name, service);

		String res = app.echo(msg);
        System.out.println("res=" + res);
    }

    public static void usage() {
        System.out.println("usage: {name} {message}");
    }
}


package com.balionis.java1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyApp {

    private static final Log LOGGER = LogFactory.getLog(MyApp.class);
	
	private final String name;
    private final MyService service;

    public MyApp(String name, MyService service) {
        this.name = name;
        this.service = service;
    }
	
    public String echo(String msg) {
        LOGGER.debug("echo: name=" + name + ", msg=" + msg);
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

        System.out.println(app.echo(msg));
    }

    public static void usage() {
        System.out.println("usage: {name} {message}");
    }
}


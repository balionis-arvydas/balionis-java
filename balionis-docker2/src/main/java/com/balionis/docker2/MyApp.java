package com.balionis.docker2;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class MyApp {

    private static Logger logger = LoggerFactory.getLogger(MyApp.class);

	private String name;

    public MyApp(String name) {
        this.name = name;
    }
	
    public String echo(String msg) {
        logger.info("echo: msg={}", msg);
        return name + ":" + msg;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            usage();
            System.exit(2);
        }

        String name = args[0];
		String msg = args[1];

        MyApp app = new MyApp(name);

        System.out.println(app.echo(msg));
    }

    public static void usage() {
        System.out.println("usage: {name} {message}");
    }
}

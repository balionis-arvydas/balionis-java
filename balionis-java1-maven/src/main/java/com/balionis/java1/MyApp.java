package com.balionis.java1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyApp {

    private static final Log LOGGER = LogFactory.getLog(MyApp.class);
	
	private String name;

    public MyApp(String name) {
        this.name = name;
    }
	
    public String echo(String msg) {
        LOGGER.debug("echo: name=" + name + ", msg=" + msg);
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

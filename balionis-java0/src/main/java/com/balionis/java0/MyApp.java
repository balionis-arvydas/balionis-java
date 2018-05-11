package com.balionis.java0;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyApp {

    private static final Log logger = LogFactory.getLog(MyApp.class);
	
    public String echo(String msg) {
        logger.debug("echo: msg=" + msg);

        return "echo:" + msg;
    }

    public static void main(String[] args) {
		String msg = (args.length > 0) ? args[0] : "myMsg";

        MyApp app = new MyApp();

		String res = app.echo(msg);

        logger.debug("main: res=" + res);

        logger.debug("main: done");
    }
}


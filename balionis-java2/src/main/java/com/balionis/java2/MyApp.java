package com.balionis.java2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.function.Function;

public class MyApp {

    public static class MyFunction implements Function<String, String> {
        public String apply(String arg) {
            return arg + ":fun1";
        }
    }

    private static final Log LOGGER = LogFactory.getLog(MyApp.class);
	
	private String name;
	private boolean useStatic;

    public MyApp(String name, boolean useStatic) {
        this.name = name;
        this.useStatic = useStatic;
    }
	
    public String echo(String msg) {
        LOGGER.debug("echo: name=" + name + ", useStatic=" + useStatic + ", msg=" + msg);
        return useStatic ? echo1(msg, MyApp::echo2) : echo1(msg, new MyFunction());
    }

    public String echo1(String msg, Function<String, String> fun) {
        return name + ":" + fun.apply(msg);
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            usage();
            System.exit(2);
        }

        String name = args[0];
		String msg = args[1];
		boolean useStatic = Boolean.parseBoolean(args[2]);

        MyApp app = new MyApp(name, useStatic);

        System.out.println(app.echo(msg));
    }

    public static void usage() {
        System.out.println("usage: {name} {message} {useStatic}");
    }

    public static String echo2(String msg) {
        return msg + ":fun2";
    }

}

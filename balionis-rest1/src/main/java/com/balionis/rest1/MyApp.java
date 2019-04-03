package com.balionis.rest1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MyApp {

    private static final Log LOGGER = LogFactory.getLog(MyApp.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
            System.exit(2);
        }

        int port = Integer.parseInt(args[0]);
        LOGGER.debug("main: port=" + port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(port);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class,
                "/rest/*");
        jerseyServlet.setInitOrder(1);
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.packages",
                "com.balionis.rest1");

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception exc) {
            LOGGER.error("main: port=" + port, exc);
        } finally {
            jettyServer.destroy();
        }
        LOGGER.debug("main:-");
    }

    public static void usage() {
        System.out.println("usage: {port}");
    }
}


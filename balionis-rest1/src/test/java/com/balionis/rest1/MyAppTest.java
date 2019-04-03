package com.balionis.rest1;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 */
public class MyAppTest {

    private static Server jettyServer;
    private static URI serverUri;

    @BeforeClass
    public static void beforeTest() throws Exception {

        jettyServer = new Server();
        ServerConnector connector = new ServerConnector(jettyServer);
        connector.setPort(0); // auto-bind to available port
        jettyServer.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class,
                "/rest/*");
        jerseyServlet.setInitOrder(1);
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.packages",
                "com.balionis.rest1");

        jettyServer.start();

        String host = connector.getHost();
        if (host == null) {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        serverUri = new URI(String.format("http://%s:%d/", host, port));

    }

    @AfterClass
    public static void afterTest() {
        try {
            jettyServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMe() throws Exception {

        HttpURLConnection http = (HttpURLConnection) serverUri.resolve("/rest/my-service/echo?msg=echo").toURL().openConnection();
        http.connect();

        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));

        BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));

        String expected = "{\"status\":0,\"reason\":null,\"payload\":\"echo\"}";
        String actual = br.lines().collect(Collectors.joining());

        assertThat("Response Body", actual, is(expected));

    }

    @Test
    public void testError() throws Exception {

        HttpURLConnection http = (HttpURLConnection) serverUri.resolve("/rest/my-service/echo?msg=error").toURL().openConnection();
        http.connect();

        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));

        BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));

        String expected = "{\"status\":1,\"reason\":\"demo error\",\"payload\":null}";
        String actual = br.lines().collect(Collectors.joining());

        assertThat("Response Body", actual, is(expected));

    }
}

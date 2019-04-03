package com.balionis.rest1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/my-service")
public class MyService {

    private static final Log LOGGER = LogFactory.getLog(MyService.class);

    @GET
    @Path("echo")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse<String> echo(@QueryParam("msg") String msg) {
        LOGGER.info("echo: msg=" + msg);

        MyResponse<String> res;
        if (!"error".equals(msg)) {
            res = new MyResponse<>(msg);
        } else {
            res = new MyResponse<>(1, "demo error");
        }

        LOGGER.info("echo: res=" + res);

        return res;
    }
}

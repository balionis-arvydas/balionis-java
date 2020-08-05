package com.balionis.aws1;

import java.util.Arrays;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Greeter implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String name, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("handleRequest: name=" + name);
        return "Hello, " + name;
    }
}


package com.balionis.spring5.standalone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringBootApplication
public class MyApp implements ApplicationRunner {

    private static final Log logger = LogFactory.getLog(MyApp.class);

    @Autowired
    private MyService service;
	
    public static void main(String[] args) {

        // SpringApplication.run(MyApp.class, args);

        SpringApplication app = new SpringApplication(MyApp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(ApplicationArguments args) {

        logger.info("run: args=" + toString(args));

        List<String> msgList = args.getOptionValues("msg");
        String msg = msgList != null ? msgList.get(0) : "myApp";

        String res = service.echo(msg);

        logger.info("run: res=" + res);

    }

    public String toString(ApplicationArguments args) {
        StringBuilder buf = new StringBuilder();
        boolean first = true;
        for (String name : args.getOptionNames()){
            if (!first){
                buf.append(", ");
            } else {
                first = false;
            }
            buf.append("arg[" + name + "]=" + args.getOptionValues(name));
        }
        return "{" + buf.toString() + "}";
    }
}


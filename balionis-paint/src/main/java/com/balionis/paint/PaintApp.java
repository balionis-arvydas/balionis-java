package com.balionis.paint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.Banner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class PaintApp implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(PaintApp.class);

    @Autowired
    private PaintRunner runner;

    public static void main(String[] args) {
        logger.debug("main: args={}", Arrays.toString(args));

        SpringApplication app = new SpringApplication(PaintApp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(ApplicationArguments args) {

        runner.run();

        logger.info("run: done");
    }
}


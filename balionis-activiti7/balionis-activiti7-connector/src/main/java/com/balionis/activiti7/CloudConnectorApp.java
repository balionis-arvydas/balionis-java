package com.balionis.activiti7;

import org.activiti.cloud.connectors.starter.configuration.EnableActivitiCloudConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableActivitiCloudConnector
public class CloudConnectorApp {

    public static void main(String[] args) {
        SpringApplication.run(CloudConnectorApp.class, args);
    }
}

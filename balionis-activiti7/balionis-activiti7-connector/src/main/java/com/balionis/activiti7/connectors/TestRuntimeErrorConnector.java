package com.balionis.activiti7.connectors;

import org.activiti.cloud.api.process.model.IntegrationRequest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(TestRuntimeErrorConnector.Channels.class)
public class TestRuntimeErrorConnector {

    public interface Channels {

        String CHANNEL = "testErrorConnectorInput";

        @Input(CHANNEL)
        SubscribableChannel testErrorConnectorInput();
    }

    @StreamListener(value = Channels.CHANNEL)
    public void handle(IntegrationRequest integrationRequest) {
        throw new RuntimeException("TestErrorConnector");
    }
}

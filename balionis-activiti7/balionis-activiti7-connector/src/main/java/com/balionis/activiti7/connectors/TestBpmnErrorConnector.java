package com.balionis.activiti7.connectors;

import org.activiti.cloud.api.process.model.CloudBpmnError;
import org.activiti.cloud.api.process.model.IntegrationRequest;
import org.activiti.cloud.connectors.starter.channels.IntegrationErrorSender;
import org.activiti.cloud.connectors.starter.configuration.ConnectorProperties;
import org.activiti.cloud.connectors.starter.model.IntegrationErrorBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(TestBpmnErrorConnector.Channels.class)
public class TestBpmnErrorConnector {

    private IntegrationErrorSender integrationErrorSender;
    private ConnectorProperties connectorProperties;

    public TestBpmnErrorConnector(IntegrationErrorSender integrationErrorSender,
        ConnectorProperties connectorProperties) {
        this.integrationErrorSender = integrationErrorSender;
        this.connectorProperties = connectorProperties;
    }

    public interface Channels {

        String CHANNEL = "testBpmnErrorConnectorInput";

        @Input(CHANNEL)
        SubscribableChannel testBpmnErrorConnectorInput();
    }

    @StreamListener(value = Channels.CHANNEL)
    public void handle(IntegrationRequest integrationRequest) {
        CloudBpmnError bpmnError = new CloudBpmnError("CLOUD_BPMN_ERROR");
        integrationErrorSender.send(IntegrationErrorBuilder.errorFor(integrationRequest, connectorProperties, bpmnError).buildMessage());
    }
}

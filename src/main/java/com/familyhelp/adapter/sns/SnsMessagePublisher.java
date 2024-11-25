package com.familyhelp.adapter.sns;

import com.familyhelp.core.gateway.MessagePublisher;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

public class SnsMessagePublisher implements MessagePublisher {

    private String topicArn = System.getenv("TOPIC_ARN");
    private final SnsClient snsClient;

    public SnsMessagePublisher() {
        this.snsClient = SnsClientSingleton.getInstance();
    }

    public SnsMessagePublisher(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    @Override
    public String publish(String message) {

        try {
            PublishRequest publicRequest = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(message)
                    .build();

            PublishResponse publishResponse = this.snsClient.publish(publicRequest);
            String success = "SUCCESS";
            return success;

        } catch (Exception e) {

            String error = "Falha ao enviar mensagem: " + e.getMessage();
            return error;
        }
    }
}

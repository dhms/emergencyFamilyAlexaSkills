package com.familyhelp.lambda.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.familyhelp.adapter.sns.SnsMessagePublisher;
import com.familyhelp.core.gateway.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class EmergencyHandler implements RequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmergencyHandler.class);
    private final MessagePublisher messagePublisher;

    public EmergencyHandler() {
        this.messagePublisher = new SnsMessagePublisher();
    }

    public EmergencyHandler(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }


    @Override
    public boolean canHandle(HandlerInput input) {
        return true; //input.matches(Predicates.intentName("HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        try {

            if (input == null) {
                LOGGER.error("HandlerInput é nulo. Não é possível continuar");
                return Optional.empty();
            }

            LOGGER.info("Publicando mensagem de emergência no SNS");
            String result = messagePublisher.publish("Emergencia! Pedido de ajuda recebido!");

            if("SUCCESS".equals(result)) {
                LOGGER.info("Mensagem publicada com sucesso no SNS");

                String speechText = "Ok, já chamei ajuda";
                return input.getResponseBuilder()
                        .withSpeech(speechText)
                        .withSimpleCard("Emergência", speechText)
                        .build();
            } else {
                String error = "Falha ao publicar mensagem no SNS";
                LOGGER.error(error);

                String speechText = "Desculpe, ocorreu um problema ao chamar ajuda";
                return input.getResponseBuilder()
                        .withSpeech(speechText)
                        .withSimpleCard("Emergência", speechText)
                        .build();

            }

        } catch (Exception e) {

            String error = "Falha ao enviar mensagem:  Event: "+ e.getMessage();
            LOGGER.error(error);

            return Optional.empty();

        }
    }
}

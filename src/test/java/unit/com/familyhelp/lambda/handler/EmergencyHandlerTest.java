package unit.com.familyhelp.lambda.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import com.familyhelp.core.gateway.MessagePublisher;
import com.familyhelp.lambda.handler.EmergencyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmergencyHandlerTest {

    private EmergencyHandler emergencyHandler;
    private MessagePublisher messagePublisherMock;

    @BeforeEach
    void setUp() {

        messagePublisherMock = mock(MessagePublisher.class);
        emergencyHandler = new EmergencyHandler(messagePublisherMock);
    }

    @Test
    void testHandleSuccess() {

        when(messagePublisherMock.publish(anyString()))
                    .thenReturn("SUCCESS");

        HandlerInput handlerInputMock = mock(HandlerInput.class);
        when(handlerInputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        Optional<Response> response = emergencyHandler.handle(handlerInputMock);

        // Validando o resultado
        assertTrue(response.isPresent(), "A resposta deve estar presente");
        assertTrue(response.get().getOutputSpeech().toString().contains("Ok, já chamei ajuda"),
                "A resposta deve conter o texto correto");
        verify(messagePublisherMock, times(1))
                .publish("Emergencia! Pedido de ajuda recebido!");
    }

    @Test
    void testHandleError() {

        when(messagePublisherMock.publish(anyString()))
                .thenThrow(new RuntimeException("Falha ao enviar mensagem"));

        HandlerInput handlerInputMock = mock(HandlerInput.class);
        when(handlerInputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        Optional<Response> response = emergencyHandler.handle(handlerInputMock);

        // Validando o resultado
        assertFalse(response.isPresent(), "A resposta não deve estar presente em caso de erro");
        verify(messagePublisherMock, times(1))
                .publish("Emergencia! Pedido de ajuda recebido!");
    }

    @Test
    void testCanHandle() {

        HandlerInput handlerInputMock = mock(HandlerInput.class);

        boolean canHandle = emergencyHandler.canHandle(handlerInputMock);

        // Validando o resultado
        assertTrue(canHandle, "O método canHandle deve retornar true para todas as entradas");
    }
}


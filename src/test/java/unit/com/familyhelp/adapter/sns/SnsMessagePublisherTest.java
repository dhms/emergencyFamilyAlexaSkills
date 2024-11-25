package unit.com.familyhelp.adapter.sns;

import com.familyhelp.adapter.sns.SnsMessagePublisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

public class SnsMessagePublisherTest {

    private SnsClient mockSnsClient;
    private SnsMessagePublisher snsMessagePublisher;

    private String topicArn = System.getenv("TOPIC_ARN");

    @BeforeEach
    void setUp() {
        mockSnsClient = mock(SnsClient.class);
        snsMessagePublisher =  new SnsMessagePublisher(mockSnsClient);
    }

    @Test
    void testPublishMessageToSns() {

        String input = "{\"message\": \"Preciso de ajuda!\"}";

        PublishResponse mockResponse = PublishResponse.builder()
                .messageId("123456")
                .build();
        when(mockSnsClient.publish((any(PublishRequest.class)))).thenReturn(mockResponse);

        String response = snsMessagePublisher.publish(input);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("SUCCESS", response);

        ArgumentCaptor<PublishRequest> captor = ArgumentCaptor.forClass(PublishRequest.class);
        verify(mockSnsClient).publish(captor.capture());
        PublishRequest capturedReq = captor.getValue();

        Assertions.assertEquals(topicArn, capturedReq.topicArn());
        Assertions.assertEquals(input, capturedReq.message());
    }

    @Test
    void testPublishMessageToSnsWithError() {

        String input = "{\"message\": \"Preciso de ajuda!\"}";

        when(mockSnsClient.publish((any(PublishRequest.class))))
                .thenThrow(new RuntimeException("Erro ao publicar mensagem"));

        String response = snsMessagePublisher.publish(input);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Falha ao enviar mensagem: Erro ao publicar mensagem", response);

        ArgumentCaptor<PublishRequest> captor = ArgumentCaptor.forClass(PublishRequest.class);
        verify(mockSnsClient).publish(captor.capture());

        PublishRequest capturedReq = captor.getValue();
        Assertions.assertEquals(topicArn, capturedReq.topicArn());
        Assertions.assertEquals(input, capturedReq.message());
    }
}

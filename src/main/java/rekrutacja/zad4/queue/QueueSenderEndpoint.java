package rekrutacja.zad4.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rekrutacja.zad4.repository.EmailMessage;

import java.util.Collections;

@RestController
@AllArgsConstructor
class QueueSenderEndpoint {
    private final static String QUEUE_NAME = "test";
    private final static EmailMessage fakeMessage = new EmailMessage("email@email.pl", "content", "subject");
    private ObjectMapper objectMapper;
    private ChannelFactory channelFactory;

    @GetMapping("/")
    public void testSending() throws Exception {
        Channel channel = channelFactory.channel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, Collections.emptyMap());

        String message = objectMapper.writeValueAsString(fakeMessage);
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    }
}

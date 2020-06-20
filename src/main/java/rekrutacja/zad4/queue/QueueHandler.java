package rekrutacja.zad4.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
class QueueHandler
{
    private static final String QUEUE_NAME = "test";

    private QueueMessageProcessor queueMessageProcessor;
    private ChannelFactory channelFactory;

    @PostConstruct
    void handle() throws Exception {
        Channel channel = channelFactory.channel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, Collections.emptyMap());
        channel.basicConsume(QUEUE_NAME, true, prepareCallback(queueMessageProcessor::process), consumerTag -> {});
    }

    private DeliverCallback prepareCallback(Consumer<String> consumeMessage)
    {
        return (consumerTag, delivery) -> consumeMessage.accept(new String(delivery.getBody(), StandardCharsets.UTF_8));
    }
}

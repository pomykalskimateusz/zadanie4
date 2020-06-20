package rekrutacja.zad4.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

@Service
class ChannelFactory
{
    private ConnectionFactory factory;

    public ChannelFactory()
    {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
    }

    public Channel channel() throws Exception {
        return factory
                .newConnection()
                .createChannel();
    }
}

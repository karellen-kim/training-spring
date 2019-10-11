package io.kare.spring.trainingspring.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String QUEUE_NAME = "hello";

    public static void main(final String[] args) throws IOException, TimeoutException {
/*        CachingConnectionFactory cf = new CachingConnectionFactory("127.0.0.1", 5672);
        cf.setUsername("user");
        cf.setPassword("user");

        RabbitTemplate template = new RabbitTemplate(cf);
        template.setExchange("amq.direct");
        template.setDefaultReceiveQueue("myQueue");
        template.convertAndSend("foo.bar", "Hello world");
        cf.destroy();*/
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setVirtualHost("vhost");
        factory.setUsername("user");
        factory.setPassword("user");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }

}

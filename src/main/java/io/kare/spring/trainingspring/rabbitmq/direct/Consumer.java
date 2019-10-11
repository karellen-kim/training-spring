package io.kare.spring.trainingspring.rabbitmq.direct;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

public class Consumer {

    public static void main(String[] args) {
        CachingConnectionFactory cf = new CachingConnectionFactory("127.0.0.1", 5672);
        cf.setUsername("user");
        cf.setPassword("user");

        RabbitAdmin admin = new RabbitAdmin(cf);
        Queue queue = new Queue("myQueue");
        admin.declareQueue(queue);

        //Exchange에 바인딩
        DirectExchange exchange = new DirectExchange("amq.direct");
        admin.declareBinding(BindingBuilder.bind(queue).to(exchange)
                .with("foo.bar"));

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);
        Object listener = new Object() {
            //메시지 처리
            public void handleMessage(Object foo) {
                System.out.println(foo);
            }
        };

        //메시지 리스닝
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        container.setMessageListener(adapter);
        container.setQueueNames("myQueue");
        container.start();
    }
}

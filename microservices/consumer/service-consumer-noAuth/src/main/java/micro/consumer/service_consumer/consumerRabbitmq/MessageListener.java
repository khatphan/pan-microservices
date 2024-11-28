package micro.consumer.service_consumer.consumerRabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveMessage(Message message){
        System.out.println("===>>> Received message: " + message);
    }
}

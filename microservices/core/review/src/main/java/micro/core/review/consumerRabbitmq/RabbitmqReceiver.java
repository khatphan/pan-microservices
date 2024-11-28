package micro.core.review.consumerRabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import micro.core.review.config.dto.ProductDto;

@Component
public class RabbitmqReceiver {

    // Queues
    @Value("${spring.rabbitmq.queue}")
    private String queueName;

    /*@RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveMessage(Message message){
    	System.out.println("===>>> Received message: " + message);
    }*/

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Polling Consumer
    public Object getPollingMessage() throws AmqpException {

        return rabbitTemplate.receiveAndConvert(queueName);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void rabbitmqReceiveMessage(String message) {
        System.out.println("===>>> Received message: " + message
                + " \n===>>> and Object of pollingMessage: "
                + (getPollingMessage() == null ? null : getPollingMessage().toString()));
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "${spring.rabbitmq.queue-prod}", durable = "false"))
    public void receiveRabbitmqProductUsingDto(@Payload ProductDto product) {
        System.out.println("===>>> Received Product: " + product.toString());
    }

    // Using Spring's message
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void rabbitmqReceiveProductUsingSpringMessage(@Payload ProductDto product) {
        System.out.println("===>>> Received Product: " + product.toString());
    }

    /*@RabbitListener(queues = "${spring.rabbitmq.queue-prod}")
    public void rabbitmqReceiveProductToString(String product) {
    	System.out.println("===>>> Received Product: " + product);
    }*/
}

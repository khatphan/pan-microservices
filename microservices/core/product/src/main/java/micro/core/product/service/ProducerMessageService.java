package micro.core.product.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import micro.core.product.dto.ProductDto;

@Service
public class ProducerMessageService {

    @Autowired
    private ProductService productService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${spring.rabbitmq.routingkey-product}")
    private String prod_routingKey;

    public void sendMessageRabbitmq() {

        String defaultMessage = "===>>> WELCOME TO RABBITMQ!!!";
        rabbitTemplate.convertAndSend(exchange, routingKey, defaultMessage);

        System.out.printf("\nMessage has routingKey= %s, and message=%s", routingKey, defaultMessage);
    }

    public void sendProductRabbitmq() {

        ProductDto product = productService.getProductById(1);
        rabbitTemplate.convertAndSend(exchange, prod_routingKey, product);

        System.out.printf("\nMessage has routingKey= %s, and Product= %s", prod_routingKey, product.toString());
    }
}

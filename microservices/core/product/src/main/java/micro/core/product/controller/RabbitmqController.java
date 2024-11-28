package micro.core.product.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import micro.core.product.service.ProducerMessageService;
import micro.core.product.service.ProductService;

@RestController
public class RabbitmqController {

    @Autowired
    ProducerMessageService producerMessageService;

    @PostMapping("/send-rabbitmq")
    public String sendMessageRabbitmq() {

        producerMessageService.sendMessageRabbitmq();

        return "Message sent successfully! ";
    }

    @PostMapping("/send-prod-rabbitmq")
    public String sendProductRabbitmq() {

        producerMessageService.sendProductRabbitmq();

        return "Product sent successfully! ";
    }
}

package micro.core.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import micro.core.product.dto.ProductDto;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProducerMessageServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ProducerMessageService producerMessageService;

    private static final String exchange = "pan-exchange";
    private static final String routingKey = "message-routingkey";
    private static final String prod_routingKey = "product-routingkey";

    private ProductDto productDto;
    private static final int idProduct = 1;

    @BeforeAll
    public void initSetUp() {
        productDto = new ProductDto();
        productDto.setProductId(1);
        productDto.setName("Halland");
        productDto.setWeight(566);
    }

    @Test
    void sendMessageRabbitmqTest() {
        rabbitTemplate.convertAndSend(exchange, routingKey, prod_routingKey);
        producerMessageService.sendMessageRabbitmq();
    }

    @Test
    void sendProductRabbitmqTest() {
        when(productService.getProductById(idProduct)).thenReturn(productDto);
        rabbitTemplate.convertAndSend(exchange, routingKey, prod_routingKey);
        producerMessageService.sendProductRabbitmq();
    }
}

package micro.core.review.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig implements RabbitListenerConfigurer {

    // Exchanges
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    // Queues
    @Value("${spring.rabbitmq.queue}")
    private String queueName;

    @Value("${spring.rabbitmq.queue-prod}")
    private String queueNameProd;

    // Routingkeys
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.rabbitmq.routingkey-product}")
    private String prod_routingkey;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public Queue queueProd() {
        return new Queue(queueNameProd, false);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }

    @Bean
    public Binding bindingProd(Queue queueProd, TopicExchange exchange) {
        return BindingBuilder.bind(queueProd).to(exchange).with(prod_routingkey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /*@Bean
    public MappingJackson2MessageConverter jackson2Converter() {
    	return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
    	DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
    	factory.setMessageConverter(jackson2Converter());
    	return factory;
    }*/

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        //        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }

    /*@Bean
    public ConnectionFactory connectionFactory() {
    	CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
    	connectionFactory.setUsername("guest");
    	connectionFactory.setPassword("guest");
    	return connectionFactory;
    }*/

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    /*@Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
    		SimpleRabbitListenerContainerFactoryConfigurer configurer,
    		ConnectionFactory connectionFactory,
    		MessageConverter messageConverter) {
    	SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    	configurer.configure(factory, connectionFactory);
    	factory.setConnectionFactory(connectionFactory);
    	factory.setMessageConverter(messageConverter);

    	return factory;
    }*/

    /*@Bean
    public SimpleRabbitListenerContainerFactory(
    		SimpleRabbitListenerContainerFactoryConfigurer configurer,
    		ConnectionFactory connectionFactory) {
    	SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    	configurer.configure(factory, connectionFactory);
    	factory.setConnectionFactory(connectionFactory);

    	return factory;
    }*/

}

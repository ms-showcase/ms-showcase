package io.covid.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${spring.rabbitmq.host}")
    private String address;
    @Value("${spring.rabbitmq.virtualHost}")
    private String virtualHost;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.concurrency}")
    private int concurrency;
    @Value("${spring.rabbitmq.maxConcurrency}")
    private int maxConcurrency;

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(
        final ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }


    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {

        final CachingConnectionFactory cachingConnectionFactory;

            cachingConnectionFactory = new CachingConnectionFactory();


        cachingConnectionFactory.setAddresses(this.address);
        cachingConnectionFactory.setVirtualHost(this.virtualHost);
        cachingConnectionFactory.setUsername(this.username);
        cachingConnectionFactory.setPassword(this.password);

        return cachingConnectionFactory;
    }

    @Bean("rabbitListenerContainerFactoryJson")
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactoryJson(
        final Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        final SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory =
            new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(cachingConnectionFactory());
        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(this.maxConcurrency);
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(this.concurrency);
        simpleRabbitListenerContainerFactory.setMessageConverter(jackson2JsonMessageConverter);
        simpleRabbitListenerContainerFactory.setDefaultRequeueRejected(false);
        return simpleRabbitListenerContainerFactory;
    }

}

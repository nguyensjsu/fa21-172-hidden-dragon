package com.example.hiddendragon;

import org.springframework.web.client.RestTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class Config {
    @Bean
    public RabbitMqReceiver receiver() {
        return new RabbitMqReceiver();
    }

    @Bean
    public RabbitMqSender sender() {
        return new RabbitMqSender();
    }

    @Bean
    public Queue hello() {
        return new Queue("hiddendragon");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
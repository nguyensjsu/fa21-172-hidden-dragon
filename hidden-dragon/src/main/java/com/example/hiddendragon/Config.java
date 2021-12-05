package com.example.hiddendragon;

import org.springframework.web.client.RestTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Config {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Receiver receiver1() {
            return new Receiver(1);
        }

        @Bean
        public Receiver receiver2() {
            return new Receiver(2);
        }
    }

    @Profile("sender")
    @Bean
    public Sender sender() {
        return new Sender();
    }
}
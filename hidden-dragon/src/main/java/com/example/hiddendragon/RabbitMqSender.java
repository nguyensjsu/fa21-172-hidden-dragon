package com.example.hiddendragon;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Queue;

@Service
public class RabbitMqSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(String msg){
      System.out.println("MESSAGE SENT: " + msg);
      rabbitTemplate.convertAndSend(queue.getName(),msg);

    }

}
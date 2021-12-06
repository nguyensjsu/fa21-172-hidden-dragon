package com.example.hiddendragon;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hiddendragon")
public class RabbitMqReceiver {

  @RabbitHandler
  public void receive(String message) {
      System.out.println("MESSAGE RECEIVED: " + message);
  }

}
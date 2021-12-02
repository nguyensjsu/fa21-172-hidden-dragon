package com.example.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringRabbitmqWorkersApplication {

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
			return args -> {
			};
	}

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner tutorial() {
			return new RabbitAmqpTutorialsRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitmqWorkersApplication.class, args);
	}


}
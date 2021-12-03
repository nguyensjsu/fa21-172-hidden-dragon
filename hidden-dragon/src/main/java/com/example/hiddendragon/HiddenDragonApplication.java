package com.example.hiddendragon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HiddenDragonApplication {

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
		SpringApplication.run(HiddenDragonApplication.class, args);

	}


}

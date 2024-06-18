package com.example.redpanda_ex_24_06_13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class RedpandaEx240613Application {

	public static void main(String[] args) {
		SpringApplication.run(RedpandaEx240613Application.class, args);
	}

}
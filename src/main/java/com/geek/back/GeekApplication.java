package com.geek.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GeekApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeekApplication.class, args);
	}

}

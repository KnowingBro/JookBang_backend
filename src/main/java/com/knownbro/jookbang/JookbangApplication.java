package com.knownbro.jookbang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class JookbangApplication {

	public static void main(String[] args) {
		SpringApplication.run(JookbangApplication.class, args);
	}

}

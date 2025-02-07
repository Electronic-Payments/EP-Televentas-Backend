package com.electronicpayment.televentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories
public class EpTeleventasBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpTeleventasBackendApplication.class, args);
	}

}

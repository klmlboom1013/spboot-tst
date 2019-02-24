package com.lhs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyLhsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyLhsApplication.class, args);
	}
}

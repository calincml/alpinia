package com.alpinia.valoraseries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = "com.alpinia.valoraseries.domain.repository")
public class ValoraseriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValoraseriesApplication.class, args);
	}

}

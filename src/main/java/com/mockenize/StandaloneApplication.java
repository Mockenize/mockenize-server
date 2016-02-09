package com.mockenize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ImportResource("classpath:spring-hazelcast.xml")
public class StandaloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(StandaloneApplication.class, args);
	}
}
package com.mockenize;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@ImportResource({ "classpath:applicationContext.xml" })
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class StandaloneApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		new StandaloneApplication().configure(new SpringApplicationBuilder(StandaloneApplication.class)).run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StandaloneApplication.class);
	}
}
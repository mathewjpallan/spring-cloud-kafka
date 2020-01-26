package com.binderror.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application class for all spring-boot applications.
 */
@SpringBootApplication
@ComponentScan("com.binderror")
public class SpringCloudKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudKafkaApplication.class, args);
	}

}

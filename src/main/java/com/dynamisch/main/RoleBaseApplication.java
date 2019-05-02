package com.dynamisch.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.dynamisch.main.model","com.dynamisch.main.dao.impl","com.dynamisch.main.service.impl","com.dynamisch.main.controller","com.dynamisch.main.config"})
public class RoleBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleBaseApplication.class, args);
	}

}

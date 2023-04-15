package com.ASWLogistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class ASWLogisticPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ASWLogisticPortalApplication.class, args);
	}

}

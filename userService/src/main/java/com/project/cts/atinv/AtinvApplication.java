package com.project.cts.atinv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AtinvApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtinvApplication.class, args);
	}

}

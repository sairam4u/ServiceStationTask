package com.epam.ServiceStation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceStationApplication.class, args);
	}

}

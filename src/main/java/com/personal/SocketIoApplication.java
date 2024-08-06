package com.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.personal"})
public class SocketIoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocketIoApplication.class, args);
	}

}

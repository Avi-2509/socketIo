package com.personal.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication(scanBasePackages = { "com.personal" }, exclude = {
		DataSourceAutoConfiguration.class
})
@ConfigurationPropertiesScan({ "com.personal" })
public class SocketIoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocketIoApplication.class, args);
	}

}

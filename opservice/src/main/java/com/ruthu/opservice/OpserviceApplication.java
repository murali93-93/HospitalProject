package com.ruthu.opservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.ruthu.opservice.dto.OPServiceContatInfoDto;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
@CrossOrigin(origins = "http://localhost:3000")
@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = {OPServiceContatInfoDto.class})
public class OpserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpserviceApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		return Executors.newFixedThreadPool(10);
	}
}

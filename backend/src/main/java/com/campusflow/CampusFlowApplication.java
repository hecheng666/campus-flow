package com.campusflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication //标记为启动类，用于启动 Spring Boot 应用
public class CampusFlowApplication {

	public static void main(String[] args) {		
		SpringApplication.run(CampusFlowApplication.class, args);	// 启动 Spring Boot 应用
	}

}

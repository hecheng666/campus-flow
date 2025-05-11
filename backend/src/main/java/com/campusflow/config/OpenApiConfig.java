package com.campusflow.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI campusflowOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("CampusFlow API")
                .description("教务管理系统接口文档")
                .version("1.0.0"));
    }
}

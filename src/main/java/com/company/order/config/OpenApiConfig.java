package com.company.order.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for API documentation.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("Order Management API").version("v1").description("API to manage orders"));
    }
}


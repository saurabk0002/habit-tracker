package com.example.habit_tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiSecurityConfig {

    private static final String SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI openApiWithBearerAuth() {

        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        Components components = new Components()
                .addSecuritySchemes(SCHEME_NAME, bearerAuth);

        SecurityRequirement requirement = new SecurityRequirement()
                .addList(SCHEME_NAME);

        return new OpenAPI()
                .components(components)
                .addSecurityItem(requirement);
    }
}
package com.example.habit_tracker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Apna Render URL yaha daal
        Server renderServer = new Server();
        renderServer.setUrl("https://habit-tracker-service-c1qp.onrender.com");
        renderServer.setDescription("Render Deployment Server");

        return new OpenAPI().servers(List.of(renderServer));
}
}
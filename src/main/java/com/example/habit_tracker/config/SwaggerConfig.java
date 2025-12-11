package com.example.habit_tracker.config;


import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenApiCustomizer removeSpringInternalSchemas() {
        return openApi -> {
            if (openApi == null || openApi.getComponents() == null || openApi.getComponents().getSchemas() == null) {
                return;
            }

            // collect keys first (so we don't modify the map while iterating)
            List<String> keys = openApi.getComponents()
                    .getSchemas()
                    .keySet()
                    .stream()
                    .collect(Collectors.toList());

            for (String key : keys) {
                // add any patterns / names you want to hide from Swagger UI
                if (key.startsWith("ApplicationContext")
                        || key.startsWith("ServletContext")
                        || key.startsWith("AutowireCapableBeanFactory")
                        || key.startsWith("BeanFactory")
                        || key.startsWith("FilterRegistration")
                        || key.startsWith("HttpStatusCode")
                        || key.startsWith("JspConfigDescriptor")
                        || key.startsWith("JspPropertyGroupDescriptor")
                        || key.startsWith("RedirectView")
                        || key.startsWith("SessionCookieConfig")
                        || key.startsWith("TaglibDescriptor")
                        || key.startsWith("Environment")
                        || key.startsWith("ServletRegistration")) {
                    openApi.getComponents().getSchemas().remove(key);
                }
            }
        };
    }
}
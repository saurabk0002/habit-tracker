package com.example.habit_tracker.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenApiCustomizer removeSpringInternalsAndRoot() {
        return openApi -> {
            if (openApi == null) return;

            // 1) Remove the root path "/" so root-controller redirect (or any mapping) doesn't show
            if (openApi.getPaths() != null) {
                openApi.getPaths().remove("/");
            }

            // 2) Remove any schema/component names that come from Spring internals or servlet impls
            if (openApi.getComponents() != null && openApi.getComponents().getSchemas() != null) {
                Set<String> keys = openApi.getComponents().getSchemas().keySet();

                // build a list of keys to remove (don't modify set while iterating)
                List<String> toRemove = new ArrayList<>();

                for (String key : keys) {
                    if (key == null) continue;
                    String k = key.toLowerCase();

                    // patterns / names to hide
                    if (k.startsWith("applicationcontext")
                            || k.startsWith("servletcontext")
                            || k.startsWith("autowirecapablebeanfactory")
                            || k.startsWith("beanfactory")
                            || k.startsWith("filterregistration")
                            || k.startsWith("httpstatuscode")
                            || k.startsWith("jspconfigdescriptor")
                            || k.startsWith("jsppropertygroupdescriptor")
                            || k.startsWith("redirectview")
                            || k.startsWith("sessioncookieconfig")
                            || k.startsWith("taglibdescriptor")
                            || k.startsWith("servletregistration")
                            || k.startsWith("environment")
                            || k.startsWith("requestbody")
                            || k.startsWith("responseentity")
                            || k.contains("servlet")
                            || k.contains("springframework")
                    ) {
                        toRemove.add(key);
                    }
                }

                // remove found keys
                toRemove.forEach(openApi.getComponents().getSchemas()::remove);
            }
        };
    }
}
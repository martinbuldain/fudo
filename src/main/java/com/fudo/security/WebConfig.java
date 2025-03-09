package com.fudo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // No cachear el archivo openapi.yaml
        registry.addResourceHandler("/openapi.yaml")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0);

        // Cachear el archivo AUTHORS por 24 horas
        registry.addResourceHandler("/AUTHORS")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(86400);
    }
}

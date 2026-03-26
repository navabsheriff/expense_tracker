package com.fullstack.expense_tracker.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints in the API
                .allowedOrigins("*") // Allow your React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these actions
                .allowedHeaders("*") // Allow all headers (like Authorization)
                .allowCredentials(true);
    }
}

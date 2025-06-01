package com.iot.tempcontrol.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica para todos os endpoints
                        .allowedOrigins("*") // Permite qualquer origem
                        .allowedMethods("*") // Permite todos os métodos (GET, POST, PUT, DELETE, etc.)
                        .allowedHeaders("*"); // Permite todos os headers
            }
        };
    }
}
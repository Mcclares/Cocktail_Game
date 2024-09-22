package com.ridango.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Указываем, какие URL доступны для кросс-доменных запросов
                        .allowedOrigins("http://localhost:3000")  // Указываем, откуда можно отправлять запросы (например, React-приложение на 3000 порту)
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Разрешенные методы
                        .allowedHeaders("*");  // Разрешенные заголовки
            }
        };
    }
}
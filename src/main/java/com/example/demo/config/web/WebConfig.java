package com.example.demo.config.web;

import io.swagger.models.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override //CORS 설정
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 요청에 대하여
        .allowedOrigins("http://localhost:8080", "http://localhost:5000", "http://localhost:3000", "https://localhost:8080", "https://localhost:5000", "https://localhost:3080") // local, docker, front local
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name()
                )
                .allowCredentials(true)
                .maxAge(3600);
    }
}

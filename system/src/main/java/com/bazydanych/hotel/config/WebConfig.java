package main.java.com.bazydanych.hotel.config;

import main.java.com.bazydanych.hotel.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor())
            .addPathPatterns("/api/*")
            .excludePathPatterns("/api/login")
            .excludePathPatterns("/api/register");
    }
}
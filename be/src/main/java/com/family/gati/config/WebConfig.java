package com.family.gati.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// SecurityConfig에서 CORS를 허용했기 때문에 관련설정 추가
public class WebConfig {

    private final long MAX_AGE_SECS = 3600;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.HEAD.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.DELETE.name()
                        )
                        .allowCredentials(true)
                        .allowedHeaders("*")
                        .maxAge(MAX_AGE_SECS);
            }
        };
    }
}
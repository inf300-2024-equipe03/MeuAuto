package br.unicamp.ic.inf335.meuauto.infra.cors;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("exp://192.168.1.2:8081")
                .allowedMethods("GET", "POST", "DELETE", "PUT");
    }
}

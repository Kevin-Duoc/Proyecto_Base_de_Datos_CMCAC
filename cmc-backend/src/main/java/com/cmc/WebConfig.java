package com.cmc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Permite CORS para todas las rutas que empiecen con /api
                .allowedOrigins("http://localhost:5173") // Permite solo a tu frontend de React
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Permite estos métodos HTTP
    }
}

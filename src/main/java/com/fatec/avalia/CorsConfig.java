package com.fatec.avalia;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:4200",              // Angular local
                        "https://avalialoop.live",           // Domínio principal
                        "https://www.avalialoop.live",       // Domínio com www
                        "https://avalialoop.vercel.app/",
                        "https://avalialoop-1zhvak5io-gilson-c-souzas-projects.vercel.app/"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
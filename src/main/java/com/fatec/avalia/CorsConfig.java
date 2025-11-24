package com.fatec.avalia; // Ajuste o pacote conforme sua estrutura

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
                        "https://avalia-alpha.vercel.app"    // SEU domínio da Vercel (confirme!)
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
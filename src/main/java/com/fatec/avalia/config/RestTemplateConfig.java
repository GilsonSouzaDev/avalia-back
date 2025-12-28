package com.fatec.avalia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     * Este Bean permite que o Spring injete o RestTemplate em nossos Services.
     * O RestTemplate é a ferramenta padrão do Spring para realizar chamadas
     * em APIs externas (como a API de Storage do Supabase).
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

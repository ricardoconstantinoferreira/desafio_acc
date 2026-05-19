package com.accenture.desafio_acc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class ViaCepConfig {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws";
    private static final int TIMEOUT_SEGUNDOS = 3;

    @Bean
    public RestClient viaCepRestClient() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(TIMEOUT_SEGUNDOS));
        factory.setReadTimeout(Duration.ofSeconds(TIMEOUT_SEGUNDOS));

        return RestClient.builder()
                .baseUrl(VIA_CEP_URL)
                .requestFactory(factory)
                .build();
    }
}

package br.edu.postech.shopinildo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Configuration
public class OpenFeignConfig {
    @Bean
public Decoder decoder(ObjectMapper objectMapper) {
    return new JacksonDecoder(objectMapper);
}

@Bean
public Encoder encoder(ObjectMapper objectMapper) {
    return new JacksonEncoder(objectMapper);
}
}

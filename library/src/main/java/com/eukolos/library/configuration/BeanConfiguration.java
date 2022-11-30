package com.eukolos.library.configuration;

import com.eukolos.library.client.RetrieveMessageErrorDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new RetrieveMessageErrorDecoder();
    }
    @Bean
    public ObjectMapper objectMapper() { return new ObjectMapper(); }
}

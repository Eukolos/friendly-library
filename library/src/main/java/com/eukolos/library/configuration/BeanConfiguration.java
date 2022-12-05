package com.eukolos.library.configuration;

import com.eukolos.library.client.RetrieveMessageErrorDecoder;
import com.eukolos.library.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class BeanConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new RetrieveMessageErrorDecoder();
    }
    @Bean
    public ObjectMapper objectMapper() { return new ObjectMapper(); }
    @Bean
    public EmailService emailServices() {
        return new EmailService();
    }

}

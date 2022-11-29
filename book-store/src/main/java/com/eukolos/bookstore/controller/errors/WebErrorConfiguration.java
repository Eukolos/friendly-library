package com.eukolos.bookstore.controller.errors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebErrorConfiguration {

    @Value("${bookstore.api.version}")
    private String currentApiVersion;
    @Value("${bookstore.sendreport.uri}")
    private String sendReportUri;


    @Bean
    public ErrorAttributes errorAttributes() {
        return new BookAppErrorAttributes(currentApiVersion, sendReportUri);
    }

}

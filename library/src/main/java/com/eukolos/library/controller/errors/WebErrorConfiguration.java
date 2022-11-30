package com.eukolos.library.controller.errors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebErrorConfiguration {

    @Value("${library.api.version}")
    private String currentApiVersion;
    @Value("${library.sendreport.uri}")
    private String sendReportUri;


    @Bean
    public ErrorAttributes errorAttributes() {
        return new LibraryAppErrorAttributes(currentApiVersion, sendReportUri);
    }

}

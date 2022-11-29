package com.eukolos.bookstore.controller.errors;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


public class BookAppErrorAttributes extends DefaultErrorAttributes {
    private final String currentApiVersion;
    private final String sendReportUri;

    public BookAppErrorAttributes(final String currentApiVersion, final String sendReportUri) {
        this.currentApiVersion = currentApiVersion;
        this.sendReportUri = sendReportUri;
    }
    @Override
    public Map<String, Object> getErrorAttributes(final WebRequest webRequest, ErrorAttributeOptions errorAttributeOptions){
        final Map<String, Object> defaultErrorAttributes = super.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.BINDING_ERRORS));
        final BookAppError superHeroAppError = BookAppError.fromDefaultAttributeMap(
                currentApiVersion, defaultErrorAttributes, sendReportUri
        );
        return superHeroAppError.toAttributeMap();
    };
}
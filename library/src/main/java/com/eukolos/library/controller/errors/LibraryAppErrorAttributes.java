package com.eukolos.library.controller.errors;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


public class LibraryAppErrorAttributes extends DefaultErrorAttributes {
    private final String currentApiVersion;
    private final String sendReportUri;

    public LibraryAppErrorAttributes(final String currentApiVersion, final String sendReportUri) {
        this.currentApiVersion = currentApiVersion;
        this.sendReportUri = sendReportUri;
    }
    @Override
    public Map<String, Object> getErrorAttributes(final WebRequest webRequest, ErrorAttributeOptions errorAttributeOptions){
        final Map<String, Object> defaultErrorAttributes = super.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.BINDING_ERRORS));
        final LibraryAppError superHeroAppError = LibraryAppError.fromDefaultAttributeMap(
                currentApiVersion, defaultErrorAttributes, sendReportUri
        );
        return superHeroAppError.toAttributeMap();
    };
}
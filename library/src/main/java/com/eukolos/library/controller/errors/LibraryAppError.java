package com.eukolos.library.controller.errors;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LibraryAppError {

    private final String apiVersion;
    private final ErrorBlock error;

    public LibraryAppError(final String apiVersion, final String code, final String message, final String domain,
                           final String reason, final String errorMessage, final String errorReportUri) {
        this.apiVersion = apiVersion;
        this.error = new ErrorBlock(code, message, domain, reason, errorMessage, errorReportUri);
    }

    public static LibraryAppError fromDefaultAttributeMap(final String apiVersion,
                                                          final Map<String, Object> defaultErrorAttributes,
                                                          final String sendReportBaseUri) {
        // original attribute values are documented in org.springframework.boot.web.servlet.error.DefaultErrorAttributes
        return new LibraryAppError(
                apiVersion,
                ((Integer) defaultErrorAttributes.get("status")).toString(),
                (String) defaultErrorAttributes.getOrDefault("message", "no message available"),
                (String) defaultErrorAttributes.getOrDefault("path", "no domain available"),
                (String) defaultErrorAttributes.getOrDefault("error", "no reason available"),
                (String) defaultErrorAttributes.get("message"),
                sendReportBaseUri
        );
    }

    public Map<String, Object> toAttributeMap() {
        return Map.of(
                "apiVersion", apiVersion,
                "error", error
        );
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public ErrorBlock getError() {
        return error;
    }

    private static final class ErrorBlock {

        @JsonIgnore
        private final UUID uniqueId;
        private final String code;
        private final String message;
        private final List<Error> errors;

        public ErrorBlock(final String code, final String message, final String domain,
                          final String reason, final String errorMessage, final String errorReportUri) {
            this.code = code;
            this.message = message;
            this.uniqueId = UUID.randomUUID();
            this.errors = List.of(
                    new Error(domain, reason, errorMessage, errorReportUri + "?id=" + uniqueId)
            );
        }

        private ErrorBlock(final UUID uniqueId, final String code, final String message, final List<Error> errors) {
            this.uniqueId = uniqueId;
            this.code = code;
            this.message = message;
            this.errors = errors;
        }

        public static ErrorBlock copyWithMessage(final ErrorBlock s, final String message) {
            return new ErrorBlock(s.uniqueId, s.code, message, s.errors);
        }

        public UUID getUniqueId() {
            return uniqueId;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public List<Error> getErrors() {
            return errors;
        }

    }

    private record Error(
            String domain,
            String reason,
            String message,
            String sendReport
    ){}
}
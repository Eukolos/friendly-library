package com.eukolos.library.exception;


import com.eukolos.library.controller.errors.ClientError;
import com.eukolos.library.controller.errors.ErrorCode;

public class BookNotFoundException extends RuntimeException implements ErrorCode {
    private ClientError exceptionMessage;
    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(ClientError message) {
        this.exceptionMessage = message;
    }

    public BookNotFoundException(String message, ClientError clientError) {
        super(message);
        this.exceptionMessage = clientError;
    }

    public ClientError getExceptionMessage() {
        return exceptionMessage;
    }

    @Override
    public String getErrorCode() {
        return "NE-002";
    }
}

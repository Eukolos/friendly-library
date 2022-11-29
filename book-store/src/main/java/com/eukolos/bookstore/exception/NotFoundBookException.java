package com.eukolos.bookstore.exception;

import com.eukolos.bookstore.controller.errors.ErrorCode;

public class NotFoundBookException extends RuntimeException implements ErrorCode {
    public NotFoundBookException(final String message) {

        super(message);
    }
    @Override
    public String getErrorCode() {
        return "NE-001";
    }
}

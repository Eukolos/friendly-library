package com.eukolos.bookstore.exception;

import com.eukolos.bookstore.controller.errors.ErrorCode;

public class BookNotFoundException extends RuntimeException implements ErrorCode {
    public BookNotFoundException(final String message) {

        super(message);
    }
    @Override
    public String getErrorCode() {
        return "NE-001";
    }
}

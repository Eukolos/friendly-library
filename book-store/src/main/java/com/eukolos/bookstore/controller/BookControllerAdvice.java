package com.eukolos.bookstore.controller;

import com.eukolos.bookstore.controller.errors.BookAppError;
import com.eukolos.bookstore.exception.NotFoundBookException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class BookControllerAdvice {

    @Value("${bookstore.sendreport.uri}")
    private String sendReportUri;
    @Value("${bookstore.api.version}")
    private String currentApiVersion;

    @ExceptionHandler(NotFoundBookException.class)
    public ResponseEntity<BookAppError> handleNonExistingBook(HttpServletRequest request,
                                                              NotFoundBookException ex) {
        final BookAppError error = new BookAppError(
                currentApiVersion,
                ex.getErrorCode(),
                "We couldnt find book",
                "bookstore-exceptions",
                "You can't find this book right now. Try later.",
                "Invalid Isbn or Not exist in Store",
                sendReportUri
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
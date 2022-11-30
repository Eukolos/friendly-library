package com.eukolos.library.controller;

import com.eukolos.library.controller.errors.LibraryAppError;
import com.eukolos.library.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class LibraryControllerAdvice {

    @Value("${library.sendreport.uri}")
    private String sendReportUri;
    @Value("${library.api.version}")
    private String currentApiVersion;


    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<LibraryAppError> handleClient(HttpServletRequest request,
                                                                 BookNotFoundException ex) {
        final LibraryAppError error = new LibraryAppError(
                currentApiVersion,
                String.valueOf(ex.getExceptionMessage().status()),
                ex.getExceptionMessage().message(),
                ex.getExceptionMessage().path(),
                "You can't find this book right now. Try later.",
                ex.getExceptionMessage().timestamp(),
                sendReportUri
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

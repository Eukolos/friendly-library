package com.eukolos.library.controller.errors;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
@RestController
@RequestMapping({LibraryErrorController.ERROR_PATH})
public class LibraryErrorController extends AbstractErrorController {

    static final String ERROR_PATH = "/error";

    public LibraryErrorController(final ErrorAttributes errorAttributes) {
        super(errorAttributes, Collections.emptyList());
    }

    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = this.getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.BINDING_ERRORS));
        HttpStatus status = this.getStatus(request);
        return new ResponseEntity<>(body, status);
    }

}

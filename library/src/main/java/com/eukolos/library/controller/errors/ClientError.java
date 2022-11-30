package com.eukolos.library.controller.errors;


public record ClientError (String timestamp,
                           int status,
                           String error,
                           String message,
                           String path

){}

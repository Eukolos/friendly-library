package com.eukolos.library.dto;


public record BookDto(
        String title,
        int bookYear,
        String author,
        String pressName,
        Double price,
        String isbn,
        Integer amount

){}
package com.eukolos.library.dto;


public record ClientBookDto(
        String title,
        int bookYear,
        String author,
        String pressName,
        Double price,
        String isbn,
        Integer amount

){

}
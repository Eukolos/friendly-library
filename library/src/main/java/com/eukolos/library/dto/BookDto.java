package com.eukolos.library.dto;

import com.eukolos.library.model.Book;

public record BookDto(
        String title,
        int bookYear,
        String author,
        String pressName,
        Double price,
        String isbn
){
    public static BookDto convertToBookDtoDto(Book book){
        return new BookDto(
                book.getTitle(),
                book.getBookYear(),
                book.getAuthor(),
                book.getPressName(),
                book.getPrice(),
                book.getIsbn()
        );
    }

}

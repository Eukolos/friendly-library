package com.eukolos.bookstore.dto;


import com.eukolos.bookstore.model.Book;

import java.util.List;

public record BookDto(
        String title,
        int bookYear,
        String author,
        String pressName,
        Double price,
        String isbn,
        Integer amount

){
    public static BookDto convertToBookDto(Book book){
        return new BookDto(
                book.getTitle(),
                book.getBookYear(),
                book.getAuthor(),
                book.getPressName(),
                book.getPrice(),
                book.getIsbn(),
                book.getAmount());
    }
    public static List<BookDto> convertAllToBookDto(List<Book> books){
        return books.stream().map(BookDto::convertToBookDto).toList();
    }
    public static Book convertToBook(BookDto bookDto){
        return new Book(
                null,
                bookDto.title,
                bookDto.bookYear,
                bookDto.author,
                bookDto.pressName,
                bookDto.price,
                bookDto.isbn,
                bookDto.amount
        );
    }
}
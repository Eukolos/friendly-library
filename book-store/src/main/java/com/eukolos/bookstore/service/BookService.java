package com.eukolos.bookstore.service;

import com.eukolos.bookstore.dto.BookDto;
import com.eukolos.bookstore.exception.NotFoundBookException;
import com.eukolos.bookstore.model.Book;
import com.eukolos.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookDto addBook(BookDto bookDto){
        return BookDto.convertToBookDto(repository.save(BookDto.convertToBook(bookDto)));
    }

    public BookDto findByIsbn(String isbn){
        Book book = repository.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundBookException("couldnt find book with this isbn"));
        return BookDto.convertToBookDto(book);


    }

    public List<BookDto> bookList(){ return BookDto.convertAllToBookDto(repository.findAll()); }

    public BookDto updateBook(String isbn, BookDto bookDto){
        Book book = repository.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundBookException("couldnt find book with this isbn"));
        Book save = new Book(
                book.getId(),
                bookDto.title(),
                bookDto.bookYear(),
                bookDto.author(),
                bookDto.pressName(),
                bookDto.price(),
                bookDto.isbn(),
                bookDto.amount()
        );
        return BookDto.convertToBookDto(repository.save(save));
    }

    public BookDto borrowOneBook(String isbn){
        Book book = repository.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundBookException("couldnt find book with this isbn"));
        return BookDto.convertToBookDto(repository.save(Book.decAmount(book)));
    }


}

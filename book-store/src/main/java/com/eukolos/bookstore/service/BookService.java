package com.eukolos.bookstore.service;

import com.eukolos.bookstore.dto.BookDto;
import com.eukolos.bookstore.model.Book;
import com.eukolos.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return BookDto.convertToBookDto(repository.findByIsbn(isbn));
    }

    public List<BookDto> bookList(){ return BookDto.convertAllToBookDto(repository.findAll()); }

    public BookDto updateBook(String isbn, BookDto bookDto){
        Book book = repository.findByIsbn(isbn);
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
        Book book = repository.findByIsbn(isbn);
        return BookDto.convertToBookDto(repository.save(Book.decAmount(book)));
    }


}

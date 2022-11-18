package com.eukolos.bookstore.controller;

import com.eukolos.bookstore.dto.BookDto;
import com.eukolos.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookDto> getBookList(){
        return service.bookList();
    }

    @GetMapping("/{isbn}")
    public BookDto getBookByIsbn(@PathVariable String isbn){
        return service.findByIsbn(isbn);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBook(@RequestBody BookDto bookDto){
        return service.addBook(bookDto);
    }

    @PutMapping("/{isbn}")
    public BookDto updateBook(@RequestBody BookDto bookDto, @PathVariable String isbn){
        return service.updateBook(isbn,bookDto);
    }
}

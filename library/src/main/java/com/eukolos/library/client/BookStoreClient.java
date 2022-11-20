package com.eukolos.library.client;

import com.eukolos.library.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "book-service", path = "/store")
public interface BookStoreClient {
    @GetMapping
    ResponseEntity<List<BookDto>> getBookList();

    @GetMapping("/{isbn}")
    ResponseEntity<BookDto> getBookByIsbn(@PathVariable String isbn);

    @PostMapping()
    ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto);

    @PutMapping("/{isbn}")
    ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable String isbn);

    @PutMapping("/borrow/{isbn}")
    ResponseEntity<BookDto> borrowOneBook(@PathVariable String isbn);
}

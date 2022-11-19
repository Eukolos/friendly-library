package com.eukolos.library.client;

import com.eukolos.library.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "book-service", path = "/store")
public interface BookStoreClient {
    @GetMapping
    List<BookDto> getBookList();

    @GetMapping("/{isbn}")
    BookDto getBookByIsbn(@PathVariable String isbn);

    @PostMapping()
    BookDto addBook(@RequestBody BookDto bookDto);

    @PutMapping("/{isbn}")
    BookDto updateBook(@RequestBody BookDto bookDto, @PathVariable String isbn);
}

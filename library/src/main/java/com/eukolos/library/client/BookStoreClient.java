package com.eukolos.library.client;

import com.eukolos.library.dto.ClientBookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "book-service", path = "/store")
public interface BookStoreClient {
    @GetMapping
    ResponseEntity<List<ClientBookDto>> getBookList();

    @GetMapping("/{isbn}")
    ResponseEntity<ClientBookDto> getBookByIsbn(@PathVariable String isbn);

    @PostMapping()
    ResponseEntity<ClientBookDto> addBook(@RequestBody ClientBookDto clientBookDto);

    @PutMapping("/{isbn}")
    ResponseEntity<ClientBookDto> updateBook(@RequestBody ClientBookDto clientBookDto, @PathVariable String isbn);

    @PutMapping("/borrow/{isbn}")
    ResponseEntity<ClientBookDto> borrowOneBook(@PathVariable(value = "isbn") String isbn);
}

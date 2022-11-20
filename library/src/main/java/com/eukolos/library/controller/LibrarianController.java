package com.eukolos.library.controller;

import com.eukolos.library.dto.LibrarianCreateRequest;
import com.eukolos.library.dto.LibrarianDto;
import com.eukolos.library.service.LibrarianService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {
    private final LibrarianService service;

    public LibrarianController(LibrarianService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibrarianDto createLibrarian(@RequestBody LibrarianCreateRequest request){
        return service.createLibrarian(request);
    }
    @GetMapping
    public List<LibrarianDto> findAll(){
        return service.findAll();
    }
    @GetMapping("/email")
    public LibrarianDto findByEmail(@RequestParam String email){
        return service.findByEmail(email);
    }
}

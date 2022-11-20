package com.eukolos.library.service;

import com.eukolos.library.dto.LibrarianCreateRequest;
import com.eukolos.library.dto.LibrarianDto;
import com.eukolos.library.model.Librarian;
import com.eukolos.library.repository.LibrarianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianService {
    private final LibrarianRepository repository;

    public LibrarianService(LibrarianRepository repository) {
        this.repository = repository;
    }

    public LibrarianDto createLibrarian(LibrarianCreateRequest request){
        Librarian librarian = repository.save(LibrarianCreateRequest.convertToLibrarian(request));
        return LibrarianDto.convertToLibrarianDto(librarian);
    }

    public List<LibrarianDto> findAll(){
        List<Librarian> librarians = repository.findAll();
        return LibrarianDto.convertToLibrarianListDto(librarians);
    }

    public LibrarianDto findByEmail(String email){
        return LibrarianDto.convertToLibrarianDto(repository.findByEmail(email));
    }


}

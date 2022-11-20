package com.eukolos.library.dto;

import com.eukolos.library.model.Librarian;

public record LibrarianCreateRequest(
        String email,
        String password,
        String phone,
        String firstName,
        String lastName
) {
    public static Librarian convertToLibrarian(LibrarianCreateRequest request){
        return new Librarian(
                request.email,
                request.password,
                request.phone,
                request.firstName,
                request.lastName
        );
    }

}
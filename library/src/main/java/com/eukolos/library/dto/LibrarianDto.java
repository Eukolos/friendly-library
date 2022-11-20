package com.eukolos.library.dto;

import com.eukolos.library.model.Librarian;

import java.util.List;

public record LibrarianDto(
        String email,
        String phone,
        String firstName,
        String lastName
) {
    public static LibrarianDto convertToLibrarianDto(Librarian librarian){
        return new LibrarianDto(
                librarian.getEmail(),
                librarian.getPhone(),
                librarian.getFirstName(),
                librarian.getLastName()
        );
    }

    public static List<LibrarianDto> convertToLibrarianListDto(List<Librarian> librarians){
        return librarians.stream().map(librarian -> LibrarianDto.convertToLibrarianDto(librarian)).toList();
    }
}

package com.eukolos.library.repository;

import com.eukolos.library.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian, String> {
    Librarian findByEmail(String email);
}

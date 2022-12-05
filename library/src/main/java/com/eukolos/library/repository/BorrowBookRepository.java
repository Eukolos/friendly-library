package com.eukolos.library.repository;

import com.eukolos.library.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowBookRepository extends JpaRepository<Borrow,String> {
    Optional<List<Borrow>> findBorrowByExpireDate(LocalDate localDate);
}

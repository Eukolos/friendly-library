package com.eukolos.library.repository;

import com.eukolos.library.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowBookRepository extends JpaRepository<Borrow,String> {
}

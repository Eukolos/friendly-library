package com.eukolos.library.repository;

import com.eukolos.library.model.Librarian;
import com.eukolos.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {
    Member findByEmail(String email);
}

package com.eukolos.library.repository;

import com.eukolos.library.LibraryApplication;
import com.eukolos.library.config.ContainersEnvironment;
import com.eukolos.library.model.Librarian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibrarianRepositoryIT extends ContainersEnvironment {
    @Autowired
    private LibrarianRepository librarianRepository;


    @Test
    public void When_FindByEmail_Expect_ReachExistLibrarian(){
        librarianRepository.save(new Librarian(
                "1",
                "emin@gmail.com",
                "password",
                "09000000",
                "emin",
                "ksy"
        ));

        Librarian fromDb = librarianRepository.findByEmail("emin@gmail.com");

        Assertions.assertEquals("emin", fromDb.getFirstName());

    }

}

package com.eukolos.library.repository;

import com.eukolos.library.LibraryApplication;
import com.eukolos.library.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRepository {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void WhenGetProducts_ExpectEmptyList(){
        List<Book> list = bookRepository
    }
}

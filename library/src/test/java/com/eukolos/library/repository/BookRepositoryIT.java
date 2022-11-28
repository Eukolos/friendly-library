package com.eukolos.library.repository;

import com.eukolos.library.LibraryApplication;
import com.eukolos.library.config.ContainersEnvironment;
import com.eukolos.library.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRepositoryIT extends ContainersEnvironment {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void When_GetBooks_Expect_List(){
        bookRepository.save( new Book(
                "1",
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                null));
        bookRepository.save(new Book(
                "Hobbit",
                1965,
                "JRRT",
                "İthaki Yayınevi",
                15.5,
                "78910",
                null));
        List<Book> list = bookRepository.findAll();
        Assertions.assertEquals(8.0, list.get(0).getPrice());
    }

    @Test
    public void When_FindById_Expect_ReachExistBook(){
        bookRepository.save(new Book(
                "1",
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                null));

        Optional<Book> fromDb = bookRepository.findById("1");

        if (fromDb.isPresent()){
            Assertions.assertEquals(8.0, fromDb.get().getPrice());
        }
    }

}
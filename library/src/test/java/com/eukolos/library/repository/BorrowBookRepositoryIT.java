package com.eukolos.library.repository;

import com.eukolos.library.LibraryApplication;
import com.eukolos.library.config.ContainersEnvironment;
import com.eukolos.library.model.Book;
import com.eukolos.library.model.Borrow;
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
public class BorrowBookRepositoryIT extends ContainersEnvironment  {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowBookRepository borrowBookRepository;

    @Test
    public void When_GetBorrows_Expect_List(){
        borrowBookRepository.save(
                new Borrow(
                        bookRepository.save( new Book(
                                "1",
                                "Dünyanın Gözü",
                                2000,
                                "Robert Jordan",
                                "İthaki Yayınevi",
                                8.0,
                                "123456",
                                null))
                )
        );
        borrowBookRepository.save(new Borrow(bookRepository.save(new Book(
                "Hobbit",
                1965,
                "JRRT",
                "İthaki Yayınevi",
                15.5,
                "78910",
                null))));
        List<Borrow> list = borrowBookRepository.findAll();
        Assertions.assertEquals(2000, list.get(0).getBook().getBookYear());
    }

    @Test
    public void When_FindById_Expect_ReachExistBorrow(){
        borrowBookRepository.save(new Borrow("1",bookRepository.save(new Book(
                "Hobbit",
                1965,
                "JRRT",
                "İthaki Yayınevi",
                15.5,
                "78910",
                null))));

        Optional<Borrow> fromDb = borrowBookRepository.findById("1");

        if (fromDb.isPresent()){
            Assertions.assertEquals(78910, fromDb.get().getBook().getIsbn());
        }
    }

}

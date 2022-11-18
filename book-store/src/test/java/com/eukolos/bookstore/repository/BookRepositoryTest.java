package com.eukolos.bookstore.repository;

import com.eukolos.bookstore.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataMongoTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.save( new Book(
                "1",
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2));
    }

    @Test
    public void shouldBeNotEmpty() {
        assertThat(bookRepository.findAll()).isNotEmpty();
    }
}

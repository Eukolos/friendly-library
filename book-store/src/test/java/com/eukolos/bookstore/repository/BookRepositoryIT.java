package com.eukolos.bookstore.repository;

import com.eukolos.bookstore.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.net.Socket;

@Testcontainers
@SpringBootTest
public class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @Container
    public static MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:4.4.3"));

    @BeforeAll
    static void initAll(){
        container.start();

    }
    @Test
    void containerStartsAndPublicPortIsAvailable() {


        assertThatPortIsAvailable(container);
    }

    @Test
    void saveBook(){
        Book book = new Book("1","Dünyanın Gözü", 2000, "Robert Jordan", "İthaki Yayınevi", 8.0,
                "123456",
                2);
        Book b1 = bookRepository.save(book);
        Assertions.assertEquals("Dünyanın Gözü", b1.getTitle());

    }

    private void assertThatPortIsAvailable(MongoDBContainer container) {
        try {
            new Socket(container.getHost(), container.getFirstMappedPort());
        } catch (IOException e) {
            throw new AssertionError("The expected port " + container.getFirstMappedPort() + " is not available!");
        }
    }
}





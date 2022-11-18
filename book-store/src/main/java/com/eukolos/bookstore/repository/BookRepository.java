package com.eukolos.bookstore.repository;

import com.eukolos.bookstore.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, String> {
    Book findByIsbn(String isbn);
}

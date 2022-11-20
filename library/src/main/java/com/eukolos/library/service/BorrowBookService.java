package com.eukolos.library.service;

import com.eukolos.library.client.BookStoreClient;
import com.eukolos.library.dto.BookDto;
import com.eukolos.library.model.Book;
import com.eukolos.library.model.Borrow;
import com.eukolos.library.repository.BookRepository;
import com.eukolos.library.repository.BorrowBookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BorrowBookService {
    private final BorrowBookRepository repository;
    private final BookRepository bookRepository;
    private final BookStoreClient bookStoreClient;

    public BorrowBookService(BorrowBookRepository repository, BookRepository bookRepository, BookStoreClient bookStoreClient) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.bookStoreClient = bookStoreClient;
    }

    public Borrow borrowing(String isbn){
        ResponseEntity<BookDto> bookDto = bookStoreClient.borrowOneBook(isbn);
        Book book = bookRepository.save(Book.convertFromClient(bookDto.getBody()));

        return repository.save(new Borrow(book));
    }

}

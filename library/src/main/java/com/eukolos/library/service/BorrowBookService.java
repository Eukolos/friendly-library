package com.eukolos.library.service;

import com.eukolos.library.client.BookStoreClient;
import com.eukolos.library.dto.ClientBookDto;
import com.eukolos.library.exception.BookNotFoundException;
import com.eukolos.library.model.Book;
import com.eukolos.library.model.Borrow;
import com.eukolos.library.model.Member;
import com.eukolos.library.repository.BookRepository;
import com.eukolos.library.repository.BorrowBookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    protected Borrow borrowing(String isbn, Member member){
        ClientBookDto clientBookDto = bookStoreClient.borrowOneBook(isbn).getBody();
        Book book = bookRepository.save(Book.convertFromClient(clientBookDto));
        return repository.save(new Borrow(book,member));
    }

    protected List<Borrow> expiredBook(LocalDate localDate){
        return repository.findBorrowByExpireDate(localDate).orElseThrow(() ->new BookNotFoundException("no book expired"));
    }

}

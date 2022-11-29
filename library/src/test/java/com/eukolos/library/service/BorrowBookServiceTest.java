package com.eukolos.library.service;

import com.eukolos.library.client.BookStoreClient;
import com.eukolos.library.config.ContainersEnvironment;
import com.eukolos.library.dto.ClientBookDto;
import com.eukolos.library.model.Book;
import com.eukolos.library.model.Borrow;
import com.eukolos.library.repository.BookRepository;
import com.eukolos.library.repository.BorrowBookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class BorrowBookServiceTest extends ContainersEnvironment {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BorrowBookRepository borrowBookRepository;
    @Mock
    private BookStoreClient bookStoreClient;
    @InjectMocks
    private BorrowBookService borrowBookService;

    @Test
    void shouldReturnBorrowBookDtoWhenSaveNewBorrowBook() {
        //given

        Book book = new Book(
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                null
        );
        ClientBookDto clientBookDto = new ClientBookDto(
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                23
        );
        Borrow responseFromRepo = new Borrow(
                "1",
                book,
                LocalDate.now(),
                null);

        String request = "123456";

        //when

        when(bookStoreClient.borrowOneBook("123456")).thenReturn(ResponseEntity.ok(clientBookDto));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(borrowBookRepository.save(any(Borrow.class))).thenReturn(responseFromRepo);


        Borrow borrow = borrowBookService.borrowing(request);
        assertEquals(2000, borrow.getBook().getBookYear());
        assertEquals("Robert Jordan", borrow.getBook().getAuthor());
        assertEquals("123456", borrow.getBook().getIsbn());
    }

}

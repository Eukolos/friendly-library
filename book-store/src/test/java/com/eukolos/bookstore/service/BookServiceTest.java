package com.eukolos.bookstore.service;

import com.eukolos.bookstore.dto.BookDto;
import com.eukolos.bookstore.model.Book;
import com.eukolos.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "integration") // not used
class BookServiceTest {

    @InjectMocks
    private BookService service;
    @Mock
    private BookRepository repository;


    @Test
    void shouldReturnBookDtoWhenSaveNewBook() {
        //given
        Book responseFromRepo = new Book(
                "1",
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);

        BookDto request = new BookDto(
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);

        //when
        when(repository.save(any(Book.class))).thenReturn(responseFromRepo);

        BookDto response = service.addBook(request);
        assertEquals(2000, response.bookYear());
        assertEquals("Robert Jordan", response.author());
        assertEquals("123456", response.isbn());
    }

    @Test
    void shouldReturnBookDtoWhenFindByIsbn() {
        //given
        Book requestToRepo = new Book(
                "1",
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);

        Book responseFromRepo = new Book(
                "1",
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);

        BookDto request = new BookDto(
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);

        String isbn = "123456";

        //when
        when(repository.findByIsbn(isbn)).thenReturn(Optional.of(responseFromRepo));

        BookDto response = service.findByIsbn(isbn);
        assertEquals(2000, response.bookYear());
        assertEquals("Robert Jordan", response.author());
        assertEquals("123456", response.isbn());


    }

    @Test
    void shouldReturnBookDtoListWhenFindAllBook() {
        //given
        Book book1 = new Book(
                "1",
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);
        Book book2 = new Book(
                "2",
                "Yüzüklerin Efendisi",
                1960,
                "J.R.R Tolkien",
                "Metis Yayıncılık",
                8.0,
                "2131412",
                2);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        //when
        when(repository.findAll()).thenReturn(bookList);

        List<BookDto> response = service.bookList();
        assertEquals(2, response.size());
        assertEquals("Robert Jordan", response.get(0).author());
        assertEquals("2131412", response.get(1).isbn());
    }
    @Test
    void shouldReturnBookDtoWhenUpdatedExistsBook() {
        //given
        Book existsBook = new Book(
                "1",
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);

        BookDto updateRequest = new BookDto(
                "Dünyanın Gözü",
                2000,
                "İbrahim Tatlıses",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);

        Book updatedBook = new Book(
                "1",
                "Dünyanın Gözü",
                2000,
                "İbrahim Tatlıses",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);
        String isbn = "123456";


        //when
        when(repository.findByIsbn(isbn)).thenReturn(Optional.of(existsBook));
        when(repository.save(updatedBook)).thenReturn(updatedBook);

        BookDto response = service.updateBook(existsBook.getIsbn(), updateRequest);
        assertEquals(2000, response.bookYear());
        assertEquals("İbrahim Tatlıses", response.author());
        assertEquals("123456", response.isbn());
    }




}
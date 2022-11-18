package com.eukolos.bookstore.controller;

import com.eukolos.bookstore.dto.BookDto;
import com.eukolos.bookstore.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "integration") // not used
class BookControllerTest {

    @MockBean
    private BookService service;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void itShouldAddBook_WhenBookRequestBody() throws Exception {
        //given
        BookDto request = new BookDto(
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2
        );

        BookDto response = new BookDto(
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2
                );
        //when
        when(service.addBook(request)).thenReturn(response);


        mockMvc.perform(post("/store")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.title").value(request.title()))
                .andExpect(status().isCreated());
    }

    @Test
    void itShouldGetAllBook_WhenRequest() throws Exception {
        //given
        BookDto b1 = new BookDto(
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);

        BookDto b2 = new BookDto(
                "Yüzüklerin Efendisi",
                1960,
                "J.R.R Tolkien",
                "Metis Yayıncılık",
                8.0,
                "123456",
                2);

        List<BookDto> response = new ArrayList<>();
        response.add(b1);
        response.add(b2);
        //when
        when(service.bookList()).thenReturn(response);


        mockMvc.perform(get("/store")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Dünyanın Gözü"))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldFindBook_WhenGiveIsbn() throws Exception {
        //given
        String isbn = "123456";

        BookDto response = new BookDto(
                "Dünyanın Gözü",
                2000,
                "Robert Jordan",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);
        //when
        when(service.findByIsbn(isbn)).thenReturn(response);


        mockMvc.perform(get("/store/{isbn}","123456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(isbn))
                .andDo(print())
                .andExpect(jsonPath("$.isbn").value(isbn))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldUpdateBook_WhenBookRequestBodyAndIsbn() throws Exception {
        //given
        String isbn = "123456";
        BookDto request = new BookDto(
                "Dünyanın Gözü",
                2000,
                "İbrahim Tatlıses",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);

        BookDto response = new BookDto(
                "Dünyanın Gözü",
                2000,
                "İbrahim Tatlıses",
                "İthaki Yayınevi",
                8.0,
                "123456",
                2);
        //when
        when(service.updateBook(isbn,request)).thenReturn(response);


        mockMvc.perform(put("/store/{isbn}",isbn)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.author").value(request.author()))
                .andExpect(status().isOk());
    }

    private String serializeJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}
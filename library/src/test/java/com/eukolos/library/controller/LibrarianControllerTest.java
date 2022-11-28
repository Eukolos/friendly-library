package com.eukolos.library.controller;

import com.eukolos.library.config.ContainersEnvironment;
import com.eukolos.library.dto.LibrarianCreateRequest;
import com.eukolos.library.dto.LibrarianDto;
import com.eukolos.library.service.LibrarianService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class LibrarianControllerTest extends ContainersEnvironment {

    @MockBean
    private LibrarianService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldAddLibrarian_WhenLibrarianRequestBody() throws Exception {
        //given
        LibrarianCreateRequest request = new LibrarianCreateRequest(
                "emin@gmail.com",
                "password123",
                "05777777777",
                "emin",
                "ksy"
        );

        LibrarianDto response = new LibrarianDto(
                "emin@gmail.com",
                "05777777777",
                "emin",
                "ksy");
        //when
        when(service.createLibrarian(request)).thenReturn(response);


        mockMvc.perform(post("/librarian")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.email").value(request.email()))
                .andExpect(status().isCreated());
    }

    @Test
    void itShouldGetAllLibrarian_WhenRequest() throws Exception {
        LibrarianDto l1 = new LibrarianDto(
                "emin@gmail.com",
                "05777777777",
                "emin",
                "ksy");
        LibrarianDto l2 = new LibrarianDto(
                "ali@gmail.com",
                "05778988989",
                "ali",
                "veli");

        List<LibrarianDto> response = new ArrayList<>();
        response.add(l1);
        response.add(l2);
        //when
        when(service.findAll()).thenReturn(response);

        mockMvc.perform(get("/librarian")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("emin"))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldFindLibrarian_WhenGiveEmail() throws Exception {
        //given
        String email = "emin@gmail.com";

        LibrarianDto response = new LibrarianDto(
                "emin@gmail.com",
                "05777777777",
                "emin",
                "ksy"
        );
        //when
        when(service.findByEmail(email)).thenReturn(response);


        mockMvc.perform(get("/librarian/email")
                        .param("email","emin@gmail.com")
                        .content(email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(response.firstName()));
    }

    private String serializeJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}

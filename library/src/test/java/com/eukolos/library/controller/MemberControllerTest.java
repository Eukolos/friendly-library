package com.eukolos.library.controller;

import com.eukolos.library.config.ContainersEnvironment;
import com.eukolos.library.dto.*;
import com.eukolos.library.service.MemberService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class MemberControllerTest extends ContainersEnvironment {

    @MockBean
    private MemberService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldAddMember_WhenMemberRequestBody() throws Exception {
        //given
        MemberCreateRequest request = new MemberCreateRequest(
                "ali@gmail.com",
                "password123",
                "05777777777",
                "ali",
                "ksy"
        );

        List<BorrowDto> borrowsDto = new ArrayList<>();
        borrowsDto.add(new BorrowDto(
                "1",
                new BookDto("Hobbit",
                        1965,
                        "JRRT",
                        "İthaki Yayınevi",
                        15.5,
                        "78910"),
                LocalDate.of(2022,11,27),
                null
        ));

        MemberDto response = new MemberDto(
                "ali@gmail.com",
                "05777777777",
                "emin",
                "ksy",
                        borrowsDto);
        //when
        when(service.createMember(request)).thenReturn(response);


        mockMvc.perform(post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.email").value(request.email()))
                .andExpect(status().isCreated());
    }

    @Test
    void itShouldGetAllMember_WhenRequest() throws Exception {

        MemberDto l1 = new MemberDto(
                "emin@gmail.com",
                "05777777777",
                "emin",
                "ksy",
                new ArrayList<>());
        MemberDto l2 = new MemberDto(
                "ali@gmail.com",
                "05778988989",
                "ali",
                "veli",
                new ArrayList<>());

        List<MemberDto> response = new ArrayList<>();
        response.add(l1);
        response.add(l2);
        //when
        when(service.findAll()).thenReturn(response);

        mockMvc.perform(get("/member")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].firstName").value("ali"))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldFindMember_WhenGiveEmail() throws Exception {
        //given
        String email = "emin@gmail.com";

        MemberDto response = new MemberDto(
                "emin@gmail.com",
                "05777777777",
                "emin",
                "ksy",
                null
        );
        //when
        when(service.findByEmail(email)).thenReturn(response);


        mockMvc.perform(get("/member/email")
                        .param("email","emin@gmail.com")
                        .content(email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(response.firstName()));
    }

    @Test
    void itShouldAddBookToExistMember_WhenMemberRequestBody() throws Exception {
        //given
        BorrowRequest request = new BorrowRequest(
                "ali@gmail.com",
                "78910"

        );

        List<BorrowDto> borrowsDto = new ArrayList<>();
        borrowsDto.add(new BorrowDto(
                "1",
                new BookDto("Hobbit",
                        1965,
                        "JRRT",
                        "İthaki Yayınevi",
                        15.5,
                        "78910"),
                LocalDate.of(2022,11,27),
                null
        ));

        MemberDto response = new MemberDto(
                "ali@gmail.com",
                "05777777777",
                "emin",
                "ksy",
                borrowsDto);
        //when
        when(service.borrowBook(request)).thenReturn(response);


        mockMvc.perform(post("/member/borrow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.borrowDtos.[0].book.isbn").value(request.isbn()))
                .andExpect(status().isCreated());
    }



    private String serializeJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}

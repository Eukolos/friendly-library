package com.eukolos.library.service;

import com.eukolos.library.config.ContainersEnvironment;
import com.eukolos.library.dto.BorrowRequest;
import com.eukolos.library.dto.MemberCreateRequest;
import com.eukolos.library.dto.MemberDto;
import com.eukolos.library.model.Book;
import com.eukolos.library.model.Borrow;
import com.eukolos.library.model.Member;
import com.eukolos.library.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class MemberServiceTest extends ContainersEnvironment {
    @Mock
    private MemberRepository repository;
    @Mock
    private BorrowBookService borrowBookService;

    @InjectMocks
    private MemberService service;


    @Test
    void shouldReturnMemberDtoWhenSaveNewMember() {
        //given
        MemberCreateRequest request = new MemberCreateRequest(
                "emin@gmail.com",
                "password",
                "05000000",
                "emin",
                "aksoy"
        );

        Member responseFromDb = new Member(
                "emin@gmail.com",
                "password",
                "05000000",
                "emin",
                "aksoy"
        );

        //when
        when(repository.save(any(Member.class))).thenReturn(responseFromDb);

        MemberDto response = service.createMember(request);
        assertEquals("emin", response.firstName());
        assertEquals("emin@gmail.com", response.email());
    }

    @Test
    void shouldReturnMemberDtoWhenFindByEmail() {
        //given
        Member responseFromDb = new Member(
                "emin@gmail.com",
                "password",
                "05000000",
                "emin",
                "aksoy"
        );

        String email = "emin@gmail.com";

        //when
        when(repository.findByEmail(email)).thenReturn(responseFromDb);

        MemberDto response = service.findByEmail(email);
        assertEquals("aksoy", response.lastName());
        assertEquals("emin@gmail.com", response.email());



    }

    @Test
    void shouldReturnMemberDtoListWhenFindAllMember() {
        //given
        Member member1 = new Member(
                "emin@gmail.com",
                "password",
                "05000000",
                "emin",
                "aksoy"
        );
        Member member2 = new Member(
                "ali@gmail.com",
                "password",
                "03000000",
                "ali",
                "veli"
        );

        List<Member> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);
        //when
        when(repository.findAll()).thenReturn(memberList);

        List<MemberDto> response = service.findAll();
        assertEquals(2, response.size());
        assertEquals("emin@gmail.com", response.get(0).email());
        assertEquals("ali", response.get(1).firstName());
    }

    @Test
    void shouldReturnMemberDtoWhoBorrowedBookWhenRequestIsbnAndMemberEmail() {
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

        Borrow borrow = new Borrow(
                "1",
                book,
                LocalDate.now(),
                null);
        List<Borrow> borrows = new ArrayList<>();
        borrows.add(borrow);

        Member responseFromDb = new Member(
                "1",
                "emin@gmail.com",
                "password",
                "05000000",
                "emin",
                "aksoy",
                borrows
        );
        BorrowRequest borrowRequest = new BorrowRequest(
                "emin@gmail.com",
                "123456"
        );

        //when
        when(borrowBookService.borrowing(borrowRequest.isbn())).thenReturn(borrow);
        when(repository.findByEmail(borrowRequest.email())).thenReturn(responseFromDb);

        MemberDto response = service.borrowBook(borrowRequest);
        assertEquals("aksoy", response.lastName());
        assertEquals("emin@gmail.com", response.email());
        assertEquals("Robert Jordan", response.borrowDtos().get(0).book().author());
    }



}

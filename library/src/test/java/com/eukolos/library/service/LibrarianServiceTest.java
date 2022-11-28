package com.eukolos.library.service;

import com.eukolos.library.config.ContainersEnvironment;
import com.eukolos.library.dto.LibrarianCreateRequest;
import com.eukolos.library.dto.LibrarianDto;
import com.eukolos.library.model.Librarian;
import com.eukolos.library.repository.LibrarianRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class LibrarianServiceTest extends ContainersEnvironment {
    @Mock
    private LibrarianRepository repository;

    @InjectMocks
    private LibrarianService service;


    @Test
    void shouldReturnLibrarianDtoWhenSaveNewLibrarian() {
        //given
        LibrarianCreateRequest request = new LibrarianCreateRequest(
                "emin@gmail.com",
                "password",
                "05000000",
                "emin",
                "aksoy"
               );

        Librarian responseFromDb = new Librarian(
                "emin@gmail.com",
                "password",
                "05000000",
                "emin",
                "aksoy"
                );

        //when
        when(repository.save(any(Librarian.class))).thenReturn(responseFromDb);

        LibrarianDto response = service.createLibrarian(request);
        assertEquals("emin", response.firstName());
        assertEquals("emin@gmail.com", response.email());
    }

    @Test
    void shouldReturnLibrarianDtoWhenFindByEmail() {
        //given
        Librarian responseFromDb = new Librarian(
                "emin@gmail.com",
                "password",
                "05000000",
                "emin",
                "aksoy"
        );

        String email = "emin@gmail.com";

        //when
        when(repository.findByEmail(email)).thenReturn(responseFromDb);

        LibrarianDto response = service.findByEmail(email);
        assertEquals("aksoy", response.lastName());
        assertEquals("emin@gmail.com", response.email());

    }

    @Test
    void shouldReturnLibrarianDtoListWhenFindAllLibrarian() {
        //given
        Librarian librarian1 = new Librarian(
                "emin@gmail.com",
                "password",
                "05000000",
                "emin",
                "aksoy"
        );
        Librarian librarian2 = new Librarian(
                "ali@gmail.com",
                "password",
                "03000000",
                "ali",
                "veli"
        );

        List<Librarian> librarianList = new ArrayList<>();
        librarianList.add(librarian1);
        librarianList.add(librarian2);
        //when
        when(repository.findAll()).thenReturn(librarianList);

        List<LibrarianDto> response = service.findAll();
        assertEquals(2, response.size());
        assertEquals("emin@gmail.com", response.get(0).email());
        assertEquals("ali", response.get(1).firstName());
    }
}

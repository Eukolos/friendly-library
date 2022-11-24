package com.eukolos.library.repository;

import com.eukolos.library.LibraryApplication;
import com.eukolos.library.config.ContainersEnvironment;
import com.eukolos.library.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberRepositoryIT extends ContainersEnvironment {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void When_FindByEmail_Expect_ReachExistMember(){
        memberRepository.save(new Member(
                "1",
                "jack@gmail.com",
                "password",
                "09000000",
                "jack",
                "black"
        ));

        Member fromDb = memberRepository.findByEmail("jack@gmail.com");

        Assertions.assertEquals("jack", fromDb.getFirstName());

    }
}

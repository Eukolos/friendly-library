package com.eukolos.library.service;

import com.eukolos.library.dto.BorrowRequest;
import com.eukolos.library.dto.MemberCreateRequest;
import com.eukolos.library.dto.MemberDto;
import com.eukolos.library.model.Borrow;
import com.eukolos.library.model.EmailDetails;
import com.eukolos.library.model.Member;
import com.eukolos.library.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;
    private final BorrowBookService borrowBookService;
    private final EmailService emailService;


    public MemberService(MemberRepository repository, BorrowBookService borrowBookService, EmailService emailService) {
        this.repository = repository;
        this.borrowBookService = borrowBookService;
        this.emailService = emailService;
    }

    public MemberDto createMember(MemberCreateRequest request){
        Member member = repository.save(MemberCreateRequest.convertToMember(request));
        return MemberDto.convertToMemberDto(member);
    }

    public List<MemberDto> findAll(){
        List<Member> members = repository.findAll();
        return MemberDto.convertToMemberListDto(members);
    }

    public MemberDto findByEmail(String email){
        return MemberDto.convertToMemberDto(repository.findByEmail(email));
    }
    protected Member findMemberByEmail(String email){
        return repository.findByEmail(email);
    }

    public MemberDto borrowBook(BorrowRequest request){
        Member member = repository.findByEmail(request.email());
        Borrow borrow = borrowBookService.borrowing(request.isbn(), member);
        List<Borrow> borrows = member.getBorrows();
        borrows.add(borrow);
        return MemberDto.convertToMemberDto(repository.save(new Member(
                member.getId(),
                member.getEmail(),
                member.getPassword(),
                member.getPhone(),
                member.getFirstName(),
                member.getLastName(),
                borrows
        )));
    }
    @Scheduled(cron="0 0 10 * * *", zone="Europe/Istanbul")
    public void expiredBook(){
        List<Borrow> expires = borrowBookService.expiredBook(LocalDate.now());
        expires.stream().map(expire -> emailService.sendSimpleMail(
                new EmailDetails(
                        expire.getMember().getEmail(),
                        "The Book has taken by you, expired. Book: " + expire.getBook().getTitle(),
                        "Expired Book",
                        "attachment"
                )
        ));

    }
}

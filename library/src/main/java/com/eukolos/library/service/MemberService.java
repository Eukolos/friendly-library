package com.eukolos.library.service;

import com.eukolos.library.dto.BorrowRequest;
import com.eukolos.library.dto.MemberCreateRequest;
import com.eukolos.library.dto.MemberDto;
import com.eukolos.library.model.Borrow;
import com.eukolos.library.model.Member;
import com.eukolos.library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;
    private final BorrowBookService borrowBookService;

    public MemberService(MemberRepository repository, BorrowBookService borrowBookService) {
        this.repository = repository;
        this.borrowBookService = borrowBookService;
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

    public MemberDto borrowBook(BorrowRequest request){
        Borrow borrow = borrowBookService.borrowing(request.isbn());
        Member member = repository.findByEmail(request.email());
        List<Borrow> borrows = member.getBorrows();
        borrows.add(borrow);
        return MemberDto.convertToMemberDto(new Member(
                member.getId(),
                member.getEmail(),
                member.getPassword(),
                member.getPhone(),
                member.getFirstName(),
                member.getLastName(),
                borrows
        ));

    }
}

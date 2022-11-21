package com.eukolos.library.service;

import com.eukolos.library.dto.MemberCreateRequest;
import com.eukolos.library.dto.MemberDto;
import com.eukolos.library.model.Member;
import com.eukolos.library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
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
}

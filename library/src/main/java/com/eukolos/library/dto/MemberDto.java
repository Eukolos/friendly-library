package com.eukolos.library.dto;

import com.eukolos.library.model.Librarian;
import com.eukolos.library.model.Member;

import java.util.List;

public record MemberDto(
        String email,
        String phone,
        String firstName,
        String lastName
) {
    public static MemberDto convertToMemberDto(Member member){
        return new MemberDto(
                member.getEmail(),
                member.getPhone(),
                member.getFirstName(),
                member.getLastName()
        );
    }

    public static List<MemberDto> convertToMemberListDto(List<Member> members){
        return members.stream().map(member -> MemberDto.convertToMemberDto(member)).toList();
    }
}

package com.eukolos.library.dto;

import com.eukolos.library.model.Member;

import java.util.List;

public record MemberDto(
        String email,
        String phone,
        String firstName,
        String lastName,
        List<BorrowDto> borrowDtos
) {


    public static MemberDto convertToMemberDto(Member member){
        return new MemberDto(
                member.getEmail(),
                member.getPhone(),
                member.getFirstName(),
                member.getLastName(),
                BorrowDto.convertToLibrarianListDto(member.getBorrows())
        );
    }

    public static List<MemberDto> convertToMemberListDto(List<Member> members){
        return members.stream().map(MemberDto::convertToMemberDto).toList();
    }
}

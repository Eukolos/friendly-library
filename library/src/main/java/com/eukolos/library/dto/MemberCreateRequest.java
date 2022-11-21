package com.eukolos.library.dto;

import com.eukolos.library.model.Member;

public record MemberCreateRequest(
        String email,
        String password,
        String phone,
        String firstName,
        String lastName
) {
    public static Member convertToMember(MemberCreateRequest request){
        return new Member(
                request.email,
                request.password,
                request.phone,
                request.firstName,
                request.lastName);
    }

}
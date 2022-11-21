package com.eukolos.library.dto;

public record BorrowRequest(
        String email,
        String isbn
) {
}

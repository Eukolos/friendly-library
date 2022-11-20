package com.eukolos.library.dto;

import com.eukolos.library.model.Borrow;

public record Borrowing(
        BookDto bookDto,
        String borrowingId,
        String createdAt
) {
}

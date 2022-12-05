package com.eukolos.library.dto;


import com.eukolos.library.model.Borrow;

import java.util.List;

public record BorrowDto(
        String id,
        BookDto book,
        String createdDate,
        String expireDate,
        String member

)  {
   public static BorrowDto convertToBorrowDto(Borrow borrow){
      return new BorrowDto(
              borrow.getId(),
              BookDto.convertToBookDtoDto(borrow.getBook()),
              borrow.getCreatedDate().toString(),
              borrow.getExpireDate().toString(),
              borrow.getMember().getEmail()
      );
   }

   public static List<BorrowDto> convertToLibrarianListDto(List<Borrow> borrows){
      return borrows.stream().map(BorrowDto::convertToBorrowDto).toList();
   }



}
package com.eukolos.library.dto;


import com.eukolos.library.model.Borrow;
import com.eukolos.library.model.Librarian;

import java.time.LocalDate;
import java.util.List;

public record BorrowDto(
        String id,
        BookDto book,
        LocalDate createdDate,
        LocalDate expireDate

)  {
   public static BorrowDto convertToBorrowDto(Borrow borrow){
      return new BorrowDto(
              borrow.getId(),
              BookDto.convertToBookDtoDto(borrow.getBook()),
              borrow.getCreatedDate(),
              borrow.getExpireDate()
      );
   }

   public static List<BorrowDto> convertToLibrarianListDto(List<Borrow> borrows){
      return borrows.stream().map(BorrowDto::convertToBorrowDto).toList();
   }



}
package com.eukolos.library.controller;

import com.eukolos.library.dto.Borrowing;
import com.eukolos.library.model.Borrow;
import com.eukolos.library.service.BorrowBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final BorrowBookService borrowBookService;

    public MemberController(BorrowBookService borrowBookService) {
        this.borrowBookService = borrowBookService;
    }
   @GetMapping
    public Borrow deneme(){
        return borrowBookService.borrowing("123");
    }
}

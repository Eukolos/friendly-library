package com.eukolos.library.controller;

import com.eukolos.library.dto.LibrarianCreateRequest;
import com.eukolos.library.dto.LibrarianDto;
import com.eukolos.library.dto.MemberCreateRequest;
import com.eukolos.library.dto.MemberDto;
import com.eukolos.library.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto createLibrarian(@RequestBody MemberCreateRequest request){
        return service.createMember(request);
    }
    @GetMapping
    public List<MemberDto> findAll(){
        return service.findAll();
    }
    @GetMapping("/email")
    public MemberDto findByEmail(@RequestParam String email){
        return service.findByEmail(email);
    }
}

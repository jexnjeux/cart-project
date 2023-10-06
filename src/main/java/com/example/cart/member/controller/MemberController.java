package com.example.cart.member.controller;

import com.example.cart.member.model.dto.MemberDto;
import com.example.cart.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/join")
  public ResponseEntity<MemberDto.Response> join(@Valid @RequestBody MemberDto.Request request, BindingResult bindingResult) {
    return ResponseEntity.ok().body(memberService.join(request, bindingResult));
  }
}

package com.example.cart.member.controller;

import com.example.cart.common.dto.SuccessResponseDto;
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
  public ResponseEntity<SuccessResponseDto<MemberDto.Response>> join(@RequestBody @Valid MemberDto.Request request,
      BindingResult bindingResult) {
    return ResponseEntity.ok()
        .body(SuccessResponseDto.of(memberService.join(request, bindingResult)));
  }

}

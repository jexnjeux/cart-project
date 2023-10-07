package com.example.cart.member.service;

import static com.example.cart.member.type.Role.ROLE_USER;

import com.example.cart.member.model.dto.MemberDto;
import com.example.cart.member.model.entity.Member;
import com.example.cart.member.repository.MemberRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public MemberDto.Response join(MemberDto.Request request, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      log.error("join() 에러 = {}", bindingResult);
      // TODO 에러처리
    }

    boolean isExistsUsername = memberRepository.existsByUsername(request.getUsername());

    if (isExistsUsername) {
      throw new RuntimeException("이미 가입된 아이디입니다.");
    }
    String encryptedPw = bCryptPasswordEncoder.encode(request.getPassword());

    Member newMember = Member.builder()
        .username(request.getUsername())
        .password(encryptedPw)
        .role(request.getRole() == null ? ROLE_USER : request.getRole())
        .name(request.getName())
        .phone(request.getPhone())
        .birthDate(request.getBirthDate())
        .gender(request.getGender())
        .address(request.getAddress())
        .postalCode(request.getPostalCode())
        .createdDate(LocalDateTime.now())
        .build();

    return MemberDto.Response.of(memberRepository.save(newMember));
  }

}

package com.example.cart.member.service;

import static com.example.cart.common.type.ErrorCode.ALREADY_JOINED_USERNAME;
import static com.example.cart.common.type.ErrorCode.MISSING_REQUEST_BODY;
import static com.example.cart.member.type.Role.ROLE_USER;

import com.example.cart.common.exception.member.AlreadyJoinedUsernameException;
import com.example.cart.common.exception.member.MissingRequestException;
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

    public Member join(MemberDto.Request request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MissingRequestException(
                    bindingResult.getFieldError().getDefaultMessage(), MISSING_REQUEST_BODY);
        }

        boolean isExistsUsername = memberRepository.existsByUsername(request.getUsername());

        if (isExistsUsername) {
            throw new AlreadyJoinedUsernameException(ALREADY_JOINED_USERNAME);
        }
        String encryptedPw = bCryptPasswordEncoder.encode(request.getPassword());
        Member member = Member.builder()
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

        return memberRepository.save(member);
    }

}

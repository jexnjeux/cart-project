package com.example.cart.common.config.auth;

import static com.example.cart.common.type.ErrorCode.USERNAME_NOT_FOUND;

import com.example.cart.common.exception.member.MemberNotFoundException;
import com.example.cart.member.model.entity.Member;
import com.example.cart.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Member member = memberRepository.findByUsername(username)
        .orElseThrow(() -> new MemberNotFoundException(USERNAME_NOT_FOUND));
    return new PrincipalDetails(member);
  }
}

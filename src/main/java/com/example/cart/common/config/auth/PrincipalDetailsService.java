package com.example.cart.common.config.auth;

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

    System.out.println("PrincipalDetailsService.loadUserByUsername");
    Member member = memberRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("등록되지 않은 아이디입니다."));
    return new PrincipalDetails(member);
  }
}

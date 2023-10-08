package com.example.cart.common.config.jwt;

import static com.example.cart.common.type.ErrorCode.USERNAME_NOT_FOUND;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.cart.common.config.auth.PrincipalDetails;
import com.example.cart.common.exception.member.MemberNotFoundException;
import com.example.cart.member.model.entity.Member;
import com.example.cart.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private final MemberRepository memberRepository;

  public JwtAuthorizationFilter(
      AuthenticationManager authenticationManager, MemberRepository memberRepository) {
    super(authenticationManager);
    this.memberRepository = memberRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    String headerString = "Authorization";
    String tokenPrefix = "Bearer ";

    String jwtHeader = request.getHeader(headerString);
    // TODO: @Value
    String secret = "dGFibGUtc3ByaW5nLWJvb3QtcHJvamVjdC1qd3Qtc2VjcmV0LWtleQo";

    if (jwtHeader != null && jwtHeader.startsWith(tokenPrefix)) {

      String token = jwtHeader.replace(tokenPrefix, "");

      String username = JWT.require(
              Algorithm.HMAC512(secret)).build()
          .verify(token).getClaim("username").asString();

      if (username != null) {
        Member member = memberRepository.findByUsername(username)
            .orElseThrow(() -> new MemberNotFoundException(USERNAME_NOT_FOUND));

        PrincipalDetails principalDetails = new PrincipalDetails(member);

        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails,
            principalDetails.getPassword(), principalDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

      }

    }
    chain.doFilter(request, response);

  }
}

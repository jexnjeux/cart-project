package com.example.cart.common.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.cart.common.config.auth.PrincipalDetails;
import com.example.cart.member.model.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    try {
      ObjectMapper om = new ObjectMapper();
      Member member = om.readValue(request.getInputStream(), Member.class);

      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          member.getUsername(), member.getPassword());

      return authenticationManager.authenticate(authenticationToken);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

    // TODO: @Value(secret, expire)
    String secret = "dGFibGUtc3ByaW5nLWJvb3QtcHJvamVjdC1qd3Qtc2VjcmV0LWtleQo";

    String jwt = JWT.create()
        .withSubject(principalDetails.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 10)))
        .withClaim("id", principalDetails.getMember().getId())
        .withClaim("username", principalDetails.getUsername())
        .sign(Algorithm.HMAC512(secret));
    response.addHeader("Authorization", "Bearer " + jwt);
  }
}

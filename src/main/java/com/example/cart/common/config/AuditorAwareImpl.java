package com.example.cart.common.config;

import com.example.cart.common.config.auth.PrincipalDetails;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()
        || authentication.getPrincipal()
        .equals("anonymousUser")) {
      return Optional.empty();
    }

    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    return Optional.of(principal.getMember().getId().toString());
  }
}


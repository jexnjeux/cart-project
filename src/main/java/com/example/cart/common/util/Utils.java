package com.example.cart.common.util;

import static com.example.cart.common.type.ErrorCode.MISSING_REQUEST_BODY;

import com.example.cart.common.config.auth.PrincipalDetails;
import com.example.cart.common.exception.member.MissingRequestException;
import java.util.Objects;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class Utils {
  public void checkRequestBody(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new MissingRequestException(
          Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(),
          MISSING_REQUEST_BODY);
    }
  }

  public Long getMemberId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    return principalDetails.getMember().getId();
  }

}

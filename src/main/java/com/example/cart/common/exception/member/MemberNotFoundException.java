package com.example.cart.common.exception.member;

import com.example.cart.common.type.ErrorCode;
import lombok.Getter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Getter
public class MemberNotFoundException extends UsernameNotFoundException {

  private final ErrorCode errorCode;


  public MemberNotFoundException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

}

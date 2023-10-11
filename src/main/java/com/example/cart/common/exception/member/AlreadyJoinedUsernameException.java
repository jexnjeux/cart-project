package com.example.cart.common.exception.member;

import com.example.cart.common.type.ErrorCode;
import lombok.Getter;

@Getter
public class AlreadyJoinedUsernameException extends RuntimeException {

  private final ErrorCode errorCode;

  public AlreadyJoinedUsernameException( ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }


}

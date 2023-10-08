package com.example.cart.common.exception.member;

import com.example.cart.common.type.ErrorCode;
import lombok.Getter;

@Getter
public class MissingRequestException extends RuntimeException {

  private final ErrorCode errorCode;

  public MissingRequestException(String msg, ErrorCode errorCode) {
    super(msg);
    this.errorCode = errorCode;
  }

}

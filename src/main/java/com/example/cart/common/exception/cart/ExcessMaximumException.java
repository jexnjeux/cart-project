package com.example.cart.common.exception.cart;

import com.example.cart.common.type.ErrorCode;
import lombok.Getter;

@Getter
public class ExcessMaximumException extends RuntimeException {

  private final ErrorCode errorCode;

  public ExcessMaximumException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

}

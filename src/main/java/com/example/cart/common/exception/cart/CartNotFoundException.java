package com.example.cart.common.exception.cart;

import com.example.cart.common.type.ErrorCode;
import lombok.Getter;

@Getter
public class CartNotFoundException extends RuntimeException {

  private final ErrorCode errorCode;

  public CartNotFoundException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }
}

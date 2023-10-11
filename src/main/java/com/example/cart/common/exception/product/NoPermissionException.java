package com.example.cart.common.exception.product;

import com.example.cart.common.type.ErrorCode;
import lombok.Getter;

@Getter
public class NoPermissionException extends RuntimeException{

  private final ErrorCode errorCode;

  public NoPermissionException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

}

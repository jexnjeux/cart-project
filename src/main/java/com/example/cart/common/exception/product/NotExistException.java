package com.example.cart.common.exception.product;

import com.example.cart.common.type.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistException extends RuntimeException {

  private final ErrorCode errorCode;

  public NotExistException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }


}

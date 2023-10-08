package com.example.cart.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  USERNAME_NOT_FOUND("존재하지 않는 아이디입니다"),
  ALREADY_JOINED_USERNAME("이미 가입된 아이디입니다"),
  MISSING_REQUEST_BODY("필수 입력 사항이 입력되지 않았습니다");

  private final String description;
}

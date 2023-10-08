package com.example.cart.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class BaseResponseDto {

  boolean success;
  int code;

  public BaseResponseDto(boolean success, int code) {
    this.success = success;
    this.code = code;

  }
}

package com.example.cart.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SuccessResponseDto<T> extends BaseResponseDto {

  private final T data;

  private SuccessResponseDto(T data) {
    super(true);
    this.data = data;
  }

  public static <T> SuccessResponseDto<T> of(T data) {
    return new SuccessResponseDto<>(data);
  }

}

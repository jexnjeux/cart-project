package com.example.cart.common.dto;

import com.example.cart.common.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FailResponseDto extends BaseResponseDto {

  private ErrorCode errorCode;
  private String errorMessage;


  private FailResponseDto(int code, ErrorCode errorCode) {
    super(false, code);
    this.errorCode = errorCode;
    this.errorMessage = errorCode.getDescription();
  }

  private FailResponseDto(int code, ErrorCode errorCode, String errorMessage) {
    super(false, code);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public static FailResponseDto of(int code, ErrorCode errorCode) {
    return new FailResponseDto(code, errorCode);
  }

  public static FailResponseDto of(int code, ErrorCode errorCode, String errorMessage) {
    return new FailResponseDto(code, errorCode, errorMessage);
  }

}

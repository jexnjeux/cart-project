package com.example.cart.common.dto;

import com.example.cart.common.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class FailResponseDto extends BaseResponseDto {

  private ErrorCode errorCode;
  private String errorMessage;


  private FailResponseDto(ErrorCode errorCode) {
    super(false);
    this.errorCode = errorCode;
    this.errorMessage = errorCode.getDescription();
  }

  private FailResponseDto(ErrorCode errorCode, String errorMessage) {
    super(false);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public static FailResponseDto of(ErrorCode errorCode) {
    return new FailResponseDto(errorCode);
  }

  public static FailResponseDto of(ErrorCode errorCode, String errorMessage) {
    return new FailResponseDto(errorCode, errorMessage);
  }

}

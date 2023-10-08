package com.example.cart.common.exception;

import com.example.cart.common.dto.FailResponseDto;
import com.example.cart.common.exception.member.AlreadyJoinedUsernameException;
import com.example.cart.common.exception.member.MemberNotFoundException;
import com.example.cart.common.exception.member.MissingRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {

  @ExceptionHandler(MemberNotFoundException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public FailResponseDto usernameNotFoundExceptionHandler(
      MemberNotFoundException e) {
    return FailResponseDto.of(HttpStatus.UNAUTHORIZED.value(), e.getErrorCode());
  }

  @ExceptionHandler(AlreadyJoinedUsernameException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public FailResponseDto alreadyJoinedUsernameExceptionHandler(AlreadyJoinedUsernameException e) {
    return FailResponseDto.of(HttpStatus.CONFLICT.value(), e.getErrorCode());
  }

  @ExceptionHandler(MissingRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public FailResponseDto missingRequestExceptionHandler(MissingRequestException e) {
    return FailResponseDto.of(HttpStatus.BAD_REQUEST.value(), e.getErrorCode(), e.getMessage());
  }

}

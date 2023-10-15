package com.example.cart.common.exception;

import com.example.cart.common.dto.FailResponseDto;
import com.example.cart.common.exception.cart.CartNotFoundException;
import com.example.cart.common.exception.cart.ExcessMaximumException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartExceptionHandler {

  @ExceptionHandler(ExcessMaximumException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public FailResponseDto excessMaximumExceptionHandler(ExcessMaximumException e) {
    return FailResponseDto.of(e.getErrorCode());
  }

  @ExceptionHandler(CartNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public FailResponseDto cartNotFoundExceptionHandler(CartNotFoundException e) {
    return FailResponseDto.of(e.getErrorCode());
  }

}

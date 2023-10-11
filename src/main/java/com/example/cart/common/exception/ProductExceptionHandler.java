package com.example.cart.common.exception;


import com.example.cart.common.dto.FailResponseDto;
import com.example.cart.common.exception.product.NoPermissionException;
import com.example.cart.common.exception.product.NotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {

  @ExceptionHandler(NoPermissionException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public FailResponseDto noPermissionExceptionHandler(NoPermissionException e) {
    return FailResponseDto.of(e.getErrorCode());
  }

  @ExceptionHandler(NotExistException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public FailResponseDto notExistExceptionHandler(NotExistException e) {
    return FailResponseDto.of(e.getErrorCode());
  }
}

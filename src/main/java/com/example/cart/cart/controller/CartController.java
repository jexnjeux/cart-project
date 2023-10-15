package com.example.cart.cart.controller;

import com.example.cart.cart.model.dto.CartItemDto;
import com.example.cart.cart.model.dto.CartResponseDto;
import com.example.cart.cart.service.CartService;
import com.example.cart.common.dto.SuccessResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @PostMapping()
  public ResponseEntity<SuccessResponseDto<CartResponseDto>> addCart(
      @Valid @RequestBody CartItemDto request, BindingResult bindingResult) {
    return ResponseEntity.ok()
        .body(SuccessResponseDto.of(cartService.addCartItem(request, bindingResult)));
  }

  @PutMapping()
  public ResponseEntity<SuccessResponseDto<CartResponseDto>> modifyCart(
      @Valid @RequestBody CartItemDto request, BindingResult bindingResult) {
    return ResponseEntity.ok()
        .body(SuccessResponseDto.of(cartService.addCartItem(request, bindingResult)));
  }
}

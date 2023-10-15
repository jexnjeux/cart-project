package com.example.cart.cart.model.dto;

import com.example.cart.cart.model.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {

  private String name;
  private int quantity;

  public static CartResponseDto of(CartItem cartItem) {
    return CartResponseDto.builder()
        .name(cartItem.getProduct().getName())
        .quantity(cartItem.getQuantity())
        .build();
  }

}

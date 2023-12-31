package com.example.cart.cart.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

  @NotNull()
  private Long productId;

  @Min(value = 1, message = "수량은 1개 이상 입력해야 합니다.")
  private int quantity;

}

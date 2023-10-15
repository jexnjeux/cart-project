package com.example.cart.product.model.dto;

import com.example.cart.product.model.entity.Product;
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
public class ProductResponseDto {
  private Long id;

  private String name;
  private int price;
  private int discountRate;


  public static ProductResponseDto of(Product product) {
    return ProductResponseDto.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .discountRate(product.getDiscountRate())
        .build();
  }

}

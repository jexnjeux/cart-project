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
public class ProductDeleteResponseDto {


  private Long id;

  public static ProductDeleteResponseDto of(Product product) {
    return ProductDeleteResponseDto.builder()
        .id(product.getId())
        .build();
  }

}

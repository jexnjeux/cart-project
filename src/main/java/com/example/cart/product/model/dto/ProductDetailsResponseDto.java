package com.example.cart.product.model.dto;

import com.example.cart.product.model.entity.Product;
import com.example.cart.product.type.ProductCategory;
import com.example.cart.product.type.ProductStatus;
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
public class ProductDetailsResponseDto {

  private Long id;
  private ProductCategory category;
  private String name;
  private int price;
  private int stock;
  private ProductStatus status;
  private int discountRate;

  public static ProductDetailsResponseDto of(Product product) {
    return ProductDetailsResponseDto.builder()
        .id(product.getId())
        .category(product.getCategory())
        .name(product.getName())
        .price(product.getPrice())
        .stock(product.getStock())
        .status(product.getStatus())
        .discountRate(product.getDiscountRate())
        .build();
  }
}

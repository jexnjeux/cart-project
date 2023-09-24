package com.example.cart.product.model.dto;

import com.example.cart.product.model.entity.Product;
import java.time.LocalDateTime;
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
public class ProductDto {

  private Long id;
  private ProductCategory category;
  private String name;
  private Integer price;
  private Integer stock;
  private ProductStatus status;
  private int discountRate;
  private LocalDateTime createdDate;

  public static ProductDto of(Product product) {
    return ProductDto.builder()
        .id(product.getId())
        .category(product.getCategory())
        .name(product.getName())
        .price(product.getPrice())
        .stock(product.getStock())
        .status(product.getStatus())
        .discountRate(product.getDiscountRate())
        .createdDate(product.getCreatedDate())
        .build();
  }
}

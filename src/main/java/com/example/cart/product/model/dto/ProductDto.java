package com.example.cart.product.model.dto;

import com.example.cart.product.model.entity.Product;
import com.example.cart.product.type.ProductCategory;
import com.example.cart.product.type.ProductStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProductDto {

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    @NotNull(message = "카테고리는 필수 입력 사항입니다")
    private ProductCategory category;

    @NotBlank(message = "상품명은 필수 입력 사항입니다")
    private String name;

    @NotNull(message = "가격은 필수 입력 사항입니다")
    @Min(value = 0, message = "가격은 0원 이상 입력 가능합니다")
    private Integer price;

    @NotNull(message = "재고 수량은 필수 입력 사항입니다")
    @Min(value = 1, message = "재고 수량은 1개 이상 입력 가능합니다")
    private Integer stock;

    private ProductStatus status;

    private int discountRate;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response {

    private String name;
    private int price;
    private int discountRate;


    public static ProductDto.Response of(Product product) {
      return Response.builder()
          .name(product.getName())
          .price(product.getPrice())
          .discountRate(product.getDiscountRate())
          .build();
    }
  }


  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeleteResponse {

    private Long id;

    public static ProductDto.DeleteResponse of(Product product) {
      return DeleteResponse.builder()
          .id(product.getId())
          .build();
    }
  }

}

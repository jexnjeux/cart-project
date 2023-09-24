package com.example.cart.product.model.entity;

import static com.example.cart.product.model.dto.ProductCategory.BOOKS;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

  @Test
  @DisplayName("멤버 생성")
  void createMember () {
    // given
    Product product = Product.builder()
        .category(BOOKS)
        .name("Spring boot")
        .price(30000)
        .stock(10)
        .build();

    // when
    // then
    Assertions.assertThat(product.getPrice()).isEqualTo(30000);
  }



}
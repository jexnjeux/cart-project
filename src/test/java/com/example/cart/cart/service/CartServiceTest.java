package com.example.cart.cart.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.cart.cart.model.dto.CartItemDto;
import com.example.cart.cart.model.dto.CartResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@SpringBootTest
@TestPropertySource("classpath:application.yml")
class CartServiceTest {

  @Autowired
  private CartService cartService;

  @Test
  @WithUserDetails("test1234")
  void 장바구니_아이템_추가() {
    //given
    CartItemDto request = CartItemDto.builder()
        .productId(1L)
        .quantity(2)
        .build();

    BindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "object");
    //when
    CartResponseDto response = cartService.addCartItem(request, bindingResult);

    //then
    assertThat(response.getQuantity()).isEqualTo(1);
  }
  // 상품 추가
  // 상품 삭제
  // 상품 수장

}
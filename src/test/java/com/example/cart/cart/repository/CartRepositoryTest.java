package com.example.cart.cart.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.cart.cart.model.entity.Cart;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application.yml")
@Sql(value = "/sql/cart-repository-test-data.sql")
class CartRepositoryTest {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartItemRepository cartItemRepository;

  @Test
  void id_로_장바구니_조회시_초기엔_장바구니_비어있음() {
    //given
    //when
    Optional<Cart> optionalCart = cartRepository.findByMemberId(1L);

    //then
    Assertions.assertThat(optionalCart).isEmpty();
  }


  @Test
  void 장바구니_추가_이후_장바구니_조회_가능() {
    //given
    //when
    Optional<Cart> result = cartRepository.findByMemberId(1L);

    //then
    Assertions.assertThat(result.get().getCartItemList().size()).isEqualTo(1);
  }

}
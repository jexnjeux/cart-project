package com.example.cart.cart.controller;

import com.example.cart.cart.model.dto.CartItemDto;
import com.example.cart.cart.model.dto.CartItemModifyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = "/sql/cart-controller-test-data.sql")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class CartControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @WithUserDetails(value = "test1234")
  void 장바구니_추가() throws Exception {
    //given
    CartItemDto request = CartItemDto.builder()
        .productId(1L)
        .quantity(1)
        .build();

    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.post("/api/user/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("울 해링턴 자켓"));
  }

  @Test
  @WithUserDetails(value = "test6655")
  void 장바구니_수정() throws Exception {
    //given
    CartItemModifyDto request = CartItemModifyDto.builder()
        .productId(1L)
        .cartItemId(1L)
        .quantity(2)
        .build();

    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.put("/api/user/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  @WithUserDetails(value = "test6655")
  void 재고수량보다_많은_수량_장바구니_추가_에러() throws Exception {
    //given
    CartItemModifyDto request = CartItemModifyDto.builder()
        .productId(1L)
        .cartItemId(1L)
        .quantity(1000)
        .build();

    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.put("/api/user/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

}
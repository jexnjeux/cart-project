package com.example.cart.product.controller;

import com.example.cart.common.config.auth.PrincipalDetailsService;
import com.example.cart.product.model.dto.ProductFormDto;
import com.example.cart.product.type.ProductCategory;
import com.example.cart.product.type.ProductStatus;
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
@Sql("/sql/product-controller-test-data.sql")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private PrincipalDetailsService principalDetailsService;

  @Test
  @WithUserDetails(value = "test3333")
  void ADMIN_상품_등록() throws Exception {
    //given
//    String username = "test3333";
//    UserDetails userDetails = principalDetailsService.loadUserByUsername(username);
//    Authentication authentication = new UsernamePasswordAuthenticationToken(
//        userDetails.getUsername(), userDetails.getPassword());
//    SecurityContextHolder.getContext().setAuthentication(authentication);

    ProductFormDto form = ProductFormDto.builder()
        .category(ProductCategory.CLOTHING)
        .name("cable knit cardigan")
        .price(157000)
        .discountRate(27)
        .status(ProductStatus.ON_SALE)
        .stock(100)
        .build();

    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(form.getName()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.price").value(form.getPrice()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.data.discountRate").value(form.getDiscountRate()));
  }


  @Test
  @WithUserDetails(value = "test1234")
  void USER_는_상품_등록_에러() throws Exception {
    //given
    ProductFormDto form = ProductFormDto.builder()
        .category(ProductCategory.CLOTHING)
        .name("후드 집업")
        .price(57000)
        .discountRate(30)
        .status(ProductStatus.ON_SALE)
        .stock(100)
        .build();

    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)))
        .andExpect(MockMvcResultMatchers.status().isForbidden());

  }

  @Test
  @WithUserDetails(value = "test3333")
  void 상품_필수값_이름_누락_에러() throws Exception {
    //given
    ProductFormDto form = ProductFormDto.builder()
        .category(ProductCategory.CLOTHING)
        .price(57000)
        .discountRate(30)
        .status(ProductStatus.ON_SALE)
        .stock(100)
        .build();

    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  @WithUserDetails(value = "test3333")
  void 상품_필수값_가격_누락_에러() throws Exception {
    //given
    ProductFormDto form = ProductFormDto.builder()
        .category(ProductCategory.CLOTHING)
        .name("후드 집업")
        .discountRate(30)
        .status(ProductStatus.ON_SALE)
        .stock(100)
        .build();

    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  void 상품명_조회() throws Exception {
    //given
    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
            .param("keyword", "corduroy"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.content.length()").value(2));
  }


  @Test
  void 상품명_가격_조회() throws Exception {
    //given
    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
            .param("keyword", "corduroy")
            .param("min-price", "140000")
            .param("max-price", "160000"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.content.length()").value(1));
  }

  @Test
  @WithUserDetails(value = "test3333")
  void ADMIN_상품_수정() throws Exception {
    //given

    ProductFormDto form = ProductFormDto.builder()
        .category(ProductCategory.CLOTHING)
        .name("울 해링턴 자켓")
        .price(2570000)
        .discountRate(10)
        .status(ProductStatus.ON_SALE)
        .stock(100)
        .build();

    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/product/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("울 해링턴 자켓"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.data.discountRate").value(10));
  }


  @Test
  @WithUserDetails(value = "test3333")
  void ADMIN_수정_본인_상품_아닌_경우_에러() throws Exception {
    //given

    ProductFormDto form = ProductFormDto.builder()
        .category(ProductCategory.CLOTHING)
        .name("울 해링턴 자켓")
        .price(2570000)
        .discountRate(10)
        .status(ProductStatus.ON_SALE)
        .stock(100)
        .build();

    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/product/4")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)))
        .andExpect(MockMvcResultMatchers.status().isForbidden());
  }


  @Test
  @WithUserDetails(value = "test1234")
  void USER_상품_수정_에러() throws Exception {
    //given

    ProductFormDto form = ProductFormDto.builder()
        .category(ProductCategory.CLOTHING)
        .name("울 해링턴 자켓")
        .price(2570000)
        .discountRate(10)
        .status(ProductStatus.ON_SALE)
        .stock(100)
        .build();

    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/product/4")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)))
        .andExpect(MockMvcResultMatchers.status().isForbidden());
  }


  @Test
  @WithUserDetails(value = "test4444")
  void ADMIN_삭제_본인_상품() throws Exception {
    //given
    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/product/5"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
  }

  @Test
  @WithUserDetails(value = "test3333")
  void ADMIN_삭제_본인_상품_아닌_경우_에러() throws Exception {
    //given
    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/product/4"))
        .andExpect(MockMvcResultMatchers.status().isForbidden());
  }
}
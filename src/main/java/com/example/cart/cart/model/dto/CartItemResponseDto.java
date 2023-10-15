package com.example.cart.cart.model.dto;

import com.example.cart.cart.model.entity.CartItem;
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
public class CartItemResponseDto {

  private Long id;
  private int quantity;
  private ProductCategory category;
  private String name;
  private int price;
  private int stock;
  private ProductStatus status;
  private int discountRate;

  public static CartItemResponseDto of(CartItem cartItem) {
    Product product = cartItem.getProduct();
    return CartItemResponseDto.builder()
        .id(cartItem.getId())
        .quantity(cartItem.getQuantity())
        .category(cartItem.getProduct().getCategory())
        .name(product.getName())
        .price(product.getPrice())
        .stock(product.getStock())
        .status(product.getStatus())
        .discountRate(product.getDiscountRate())
        .build();
  }

}

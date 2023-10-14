package com.example.cart.cart.service;

import static com.example.cart.common.type.ErrorCode.CANT_PUT_IN_THE_CART;
import static com.example.cart.common.type.ErrorCode.EXCESS_MAXIMUM_QUANTITY;
import static com.example.cart.common.type.ErrorCode.NON_EXISTENT_PRODUCT;
import static com.example.cart.common.type.ErrorCode.USER_ID_NOT_FOUND;

import com.example.cart.cart.model.dto.CartItemDto;
import com.example.cart.cart.model.dto.CartResponseDto;
import com.example.cart.cart.model.entity.Cart;
import com.example.cart.cart.model.entity.CartItem;
import com.example.cart.cart.repository.CartItemRepository;
import com.example.cart.cart.repository.CartRepository;
import com.example.cart.common.exception.cart.ExcessMaximumException;
import com.example.cart.common.exception.member.MemberNotFoundException;
import com.example.cart.common.exception.product.NotExistException;
import com.example.cart.common.util.Utils;
import com.example.cart.member.model.entity.Member;
import com.example.cart.member.repository.MemberRepository;
import com.example.cart.product.model.entity.Product;
import com.example.cart.product.repository.ProductRepository;
import com.example.cart.product.type.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final MemberRepository memberRepository;
  private final ProductRepository productRepository;
  private final Utils utils;

  @Transactional
  public CartResponseDto addCartItem(CartItemDto request, BindingResult bindingResult) {

    utils.checkRequestBody(bindingResult);

    Long memberId = utils.getMemberId();
    Member member = memberRepository.findById(memberId).orElseThrow(
        () -> new MemberNotFoundException(USER_ID_NOT_FOUND)
    );

    Cart cart = cartRepository.findByMemberId(memberId).orElse(null);

    if (cart == null) {
      cart = Cart.createCart(member);
      cartRepository.save(cart);
    }

    Product product = productRepository.findById(request.getItemId())
        .orElseThrow(() -> new NotExistException(NON_EXISTENT_PRODUCT));

    if (product.getStatus().equals(ProductStatus.END_OF_SALE) || product.getStatus()
        .equals(ProductStatus.SOLD_OUT)) {
      throw new NotExistException(CANT_PUT_IN_THE_CART);
    }

    if (product.getStock() < request.getCount()) {
      throw new ExcessMaximumException(EXCESS_MAXIMUM_QUANTITY);
    }

    CartItem cartItem = CartItem.builder()
        .count(request.getCount())
        .cart(cart)
        .product(product)
        .build();

    return CartResponseDto.of(cartItemRepository.save(cartItem));
  }


}

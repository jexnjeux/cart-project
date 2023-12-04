package com.example.cart.cart.controller;

import com.example.cart.cart.model.dto.CartItemDto;
import com.example.cart.cart.model.dto.CartItemModifyDto;
import com.example.cart.cart.model.dto.CartItemResponseDto;
import com.example.cart.cart.model.dto.CartResponseDto;
import com.example.cart.cart.model.entity.CartItem;
import com.example.cart.cart.service.CartService;
import com.example.cart.common.dto.BaseResponseDto;
import com.example.cart.common.dto.SuccessResponseDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping()
    public ResponseEntity<SuccessResponseDto<CartResponseDto>> addCart(
            @Valid @RequestBody CartItemDto request, BindingResult bindingResult) {
        CartItem cartItem = cartService.addCartItem(request, bindingResult);
        return ResponseEntity.ok()
                .body(SuccessResponseDto.of(CartResponseDto.of(cartItem)));
    }

    @PutMapping()
    public ResponseEntity<SuccessResponseDto<CartResponseDto>> modifyCart(
            @Valid @RequestBody CartItemModifyDto request, BindingResult bindingResult) {
        CartItem cartItem = cartService.modifyCartItem(request, bindingResult);
        return ResponseEntity.ok()
                .body(SuccessResponseDto.of(CartResponseDto.of(cartItem)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> deleteCartItem(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(BaseResponseDto.builder().success(cartService.deleteCartItem(id)).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDto<List<CartItemResponseDto>>> getCart(
            @PathVariable Long id) {
        List<CartItem> cartItems = cartService.getCart(id);
        return ResponseEntity.ok().body(SuccessResponseDto.of(cartItems.stream()
                .map(CartItemResponseDto::of
                ).collect(Collectors.toList())));
    }

}

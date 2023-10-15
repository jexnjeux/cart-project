package com.example.cart.cart.repository;

import com.example.cart.cart.model.entity.CartItem;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  Page<CartItem> findByCartId(Long cartId, Pageable pageable);

  List<CartItem> findByCart_MemberId(Long memberId);
}

package com.example.cart.cart.repository;

import com.example.cart.cart.model.entity.CartItem;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  List<CartItem> findByCart_MemberId(Long memberId);

  List<CartItem> findByModifiedDateBefore(LocalDate cutoffDate);
}

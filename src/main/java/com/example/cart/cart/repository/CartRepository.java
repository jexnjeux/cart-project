package com.example.cart.cart.repository;

import com.example.cart.cart.model.entity.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByMemberId(Long id);
}

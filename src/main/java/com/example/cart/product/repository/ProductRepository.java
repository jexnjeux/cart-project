package com.example.cart.product.repository;

import com.example.cart.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends JpaRepository<Product, Long>,
    JpaSpecificationExecutor<Product> {

}

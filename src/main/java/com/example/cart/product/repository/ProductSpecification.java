package com.example.cart.product.repository;

import com.example.cart.product.type.ProductCategory;
import com.example.cart.product.type.ProductStatus;
import com.example.cart.product.model.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

  public static Specification<Product> findByStatusNot() {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.notEqual(root.get("status"),
            ProductStatus.END_OF_SALE);

  }

  public static Specification<Product> containingKeyword(String keyword) {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.like(root.get("name"), '%' + keyword + '%');
    };
  }

  public static Specification<Product> equalCategory(String category) {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get("category"),
          ProductCategory.valueOf(category));
    };
  }


  public static Specification<Product> betweenPrice(int minPrice, int maxPrice) {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.between(root.get("price"), minPrice,
          maxPrice);
    };
  }
}
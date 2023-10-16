package com.example.cart.product.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.cart.product.model.entity.Product;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application.yml")
@Sql("/sql/product-repository-test-data.sql")
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void 조회() {
    //given
    Specification<Product> specification = ProductSpecification.findByStatusNot();
    List<Product> productList = productRepository.findAll(specification);


    //when
    //then
    Assertions.assertThat(productList.size()).isEqualTo(9);

  }


}
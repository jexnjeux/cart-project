package com.example.cart.product.service;

import com.example.cart.product.model.dto.ProductDto;
import com.example.cart.product.model.dto.ProductRequest;
import org.springframework.validation.BindingResult;

public interface ProductService {

  ProductDto createProduct(ProductRequest.CreateDto createDto, BindingResult result);

  ProductDto modifyProduct(Long id, ProductRequest.ModifyDto modifyDto, BindingResult result);

}

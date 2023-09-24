package com.example.cart.product.service;

import static com.example.cart.product.model.dto.ProductStatus.ON_SALE;

import com.example.cart.product.model.dto.ProductDto;
import com.example.cart.product.model.dto.ProductRequest;
import com.example.cart.product.model.entity.Product;
import com.example.cart.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public ProductDto createProduct(ProductRequest.CreateDto createDto, BindingResult result) {
    if (result.hasErrors()) {
      List<FieldError> fieldErrorList = result.getFieldErrors();
      List<String> errorMessageList = new ArrayList<>();
      for (FieldError fieldError : fieldErrorList) {
        errorMessageList.add(fieldError.getDefaultMessage());
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          errorMessageList.toString());
    }

    return ProductDto.of(productRepository.save(Product.builder()
        .category(createDto.getCategory())
        .name(createDto.getName())
        .price(createDto.getPrice())
        .stock(createDto.getStock())
        .discountRate(createDto.getDiscountRate())
        .status(ON_SALE)
        .build()));
  }
}

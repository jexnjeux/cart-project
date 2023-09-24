package com.example.cart.product.service;

import static com.example.cart.product.model.dto.ProductStatus.END_OF_SALE;
import static com.example.cart.product.model.dto.ProductStatus.ON_SALE;
import static com.example.cart.product.model.dto.ProductStatus.SOLD_OUT;

import com.example.cart.product.model.dto.ProductDto;
import com.example.cart.product.model.dto.ProductRequest;
import com.example.cart.product.model.dto.ProductRequest.ModifyDto;
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

  @Override
  public ProductDto modifyProduct(Long id, ModifyDto modifyDto, BindingResult result) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "상품을 찾을 수 없습니다. "));

    if (product.getStatus() == END_OF_SALE) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제된 상품입니다.");
    }

    // TODO: createdBy 체크

    product.setCategory(modifyDto.getCategory());
    product.setName(modifyDto.getName());
    product.setPrice(modifyDto.getPrice());
    product.setStock(modifyDto.getStock());
    product.setDiscountRate(modifyDto.getDiscountRate());

    if (modifyDto.getStock() == 0) {
      product.setStatus(SOLD_OUT);
    }

    productRepository.save(product);

    return ProductDto.of(product);
  }
}

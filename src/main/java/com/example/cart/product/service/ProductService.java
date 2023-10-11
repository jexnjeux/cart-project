package com.example.cart.product.service;

import static com.example.cart.common.type.ErrorCode.ALREADY_DELETED_PRODUCT;
import static com.example.cart.common.type.ErrorCode.DELETED_PRODUCT;
import static com.example.cart.common.type.ErrorCode.MISSING_REQUEST_BODY;
import static com.example.cart.common.type.ErrorCode.NON_EXISTENT_PRODUCT;
import static com.example.cart.common.type.ErrorCode.NO_MODIFY_PERMISSION;
import static com.example.cart.product.type.ProductStatus.END_OF_SALE;
import static com.example.cart.product.type.ProductStatus.ON_SALE;
import static com.example.cart.product.type.ProductStatus.SOLD_OUT;

import com.example.cart.common.config.auth.PrincipalDetails;
import com.example.cart.common.exception.member.MissingRequestException;
import com.example.cart.common.exception.product.NoPermissionException;
import com.example.cart.common.exception.product.NotExistException;
import com.example.cart.common.type.ErrorCode;
import com.example.cart.product.model.dto.ProductDeleteResponseDto;
import com.example.cart.product.model.dto.ProductDetailsResponseDto;
import com.example.cart.product.model.dto.ProductFormDto;
import com.example.cart.product.model.dto.ProductResponseDto;
import com.example.cart.product.model.entity.Product;
import com.example.cart.product.repository.ProductRepository;
import com.example.cart.product.repository.ProductSpecification;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  public ProductResponseDto createProduct(ProductFormDto request,
      BindingResult bindingResult) {

    checkRequestBody(bindingResult);

    return ProductResponseDto.of(productRepository.save(Product.builder()
        .category(request.getCategory())
        .name(request.getName())
        .price(request.getPrice())
        .stock(request.getStock())
        .discountRate(request.getDiscountRate())
        .status(ON_SALE)
        .build()));
  }


  public ProductResponseDto modifyProduct(Long id, ProductFormDto request,
      BindingResult bindingResult) {

    checkRequestBody(bindingResult);

    Product product = getProduct(id, DELETED_PRODUCT);
    String memberId = getMemberId();

    checkCreatorId(product.getCreatedBy(), memberId);

    product.setCategory(request.getCategory());
    product.setName(request.getName());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());
    product.setDiscountRate(request.getDiscountRate());

    if (request.getStock() == 0) {
      product.setStatus(SOLD_OUT);
    }

    productRepository.save(product);

    return ProductResponseDto.of(product);
  }

  public ProductDeleteResponseDto deleteProduct(Long id) {
    Product product = getProduct(id, ALREADY_DELETED_PRODUCT);
    String memberId = getMemberId();

    checkCreatorId(product.getCreatedBy(), memberId);

    product.setDeletedDate(LocalDateTime.now());
    product.setStatus(END_OF_SALE);

    return ProductDeleteResponseDto.of(productRepository.save(product));
  }

  public Page<ProductResponseDto> getProductList(String keyword,
      String category, Integer minPrice, Integer maxPrice, Pageable pageable) {

    Specification<Product> specification = ProductSpecification.findByStatusNot()
        .and(ProductSpecification.containingKeyword(keyword));
    if (category != null) {
      specification = specification.and(ProductSpecification.equalCategory(category));
    }
    if (minPrice != null && maxPrice != null) {
      specification = specification.and(ProductSpecification.betweenPrice(minPrice, maxPrice));
    }

    return productRepository.findAll(specification, pageable)
        .map(ProductResponseDto::of);
  }

  public ProductDetailsResponseDto getProductDetails(Long id) {
    Product product = getProduct(id, DELETED_PRODUCT);

    return ProductDetailsResponseDto.of(product);
  }


  private String getMemberId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    return principalDetails.getMember().getId().toString();
  }

  private void checkRequestBody(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new MissingRequestException(
          Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(),
          MISSING_REQUEST_BODY);
    }
  }

  private Product getProduct(Long id, ErrorCode errorCode) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new NotExistException(NON_EXISTENT_PRODUCT));

    if (product.getStatus() == END_OF_SALE) {
      throw new NotExistException(errorCode);
    }
    return product;
  }

  private static void checkCreatorId(String creatorId, String memberId) {
    if (!creatorId.equals(memberId)) {
      throw new NoPermissionException(NO_MODIFY_PERMISSION);
    }
  }
}

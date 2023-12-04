package com.example.cart.product.controller;

import com.example.cart.common.dto.SuccessResponseDto;
import com.example.cart.product.model.dto.ProductDeleteResponseDto;
import com.example.cart.product.model.dto.ProductDetailsResponseDto;
import com.example.cart.product.model.dto.ProductFormDto;
import com.example.cart.product.model.dto.ProductResponseDto;
import com.example.cart.product.model.entity.Product;
import com.example.cart.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/admin/product")
    public ResponseEntity<SuccessResponseDto<ProductResponseDto>> createProduct(
            @Valid @RequestBody ProductFormDto request,
            BindingResult result) {
        Product product = productService.createProduct(request, result);
        return ResponseEntity.ok()
                .body(SuccessResponseDto.of(ProductResponseDto.of(product)));
    }

    @PutMapping("/admin/product/{id}")
    public ResponseEntity<SuccessResponseDto<ProductResponseDto>> modifyProduct(
            @PathVariable Long id,
            @RequestBody ProductFormDto request, BindingResult result) {

        Product product = productService.modifyProduct(id, request, result);
        return ResponseEntity.ok()
                .body(SuccessResponseDto.of(ProductResponseDto.of(product)));
    }

    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<SuccessResponseDto<ProductDeleteResponseDto>> deleteProduct(
            @PathVariable Long id) {
        return ResponseEntity.ok()
                .body(SuccessResponseDto.of(ProductDeleteResponseDto.of(productService.deleteProduct(id))));
    }

    @GetMapping("/products")
    public ResponseEntity<SuccessResponseDto<Page<ProductResponseDto>>> getProducts(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "min-price", required = false) Integer minPrice,
            @RequestParam(value = "max-price", required = false) Integer maxPrice
    ) {

        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = productService.getProductList(keyword, category, minPrice, maxPrice, pageable);
        return ResponseEntity.ok()
                .body(SuccessResponseDto.of(productPage.map(ProductResponseDto::of)));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<SuccessResponseDto<ProductDetailsResponseDto>> getProductDetails(@PathVariable Long id) {
        Product product = productService.getProductDetails(id);
        return ResponseEntity.ok()
                .body(SuccessResponseDto.of(ProductDetailsResponseDto.of(product)));
    }
}

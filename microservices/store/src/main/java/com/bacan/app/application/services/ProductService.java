package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.ProductUseCase;
import com.bacan.app.application.port.out.persistence.ProductDatabase;
import com.bacan.app.domain.models.product.Product;
import com.bacan.app.domain.queries.product.ProductQuery;
import com.bacan.app.domain.utilities.ApplicationTimeUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProductService implements ProductUseCase {
  private final ProductDatabase productDatabase;

  public ProductService(ProductDatabase productDatabase) {
    this.productDatabase = productDatabase;
  }

  @Override
  public Mono<Product> createProduct(Product product) {
    LocalDateTime actualDateTime = ApplicationTimeUtil.getActualDateTime();
    return productDatabase.createProduct(product
      .withCreatedAt(actualDateTime)
      .withUpdatedAt(actualDateTime));
  }

  @Override
  public Flux<Product> getAllProductsByQuery(ProductQuery productQuery) {
    return productDatabase.findAllProducts(productQuery);
  }

  @Override
  public Mono<Long> countAllProductsByQuery(ProductQuery productQuery) {
    return productDatabase.countProductsByQuery(productQuery);
  }

  @Override
  public Mono<Product> getProductById(Long productId) {
    return productDatabase.findProductById(UUID.fromString(productId));
  }
}

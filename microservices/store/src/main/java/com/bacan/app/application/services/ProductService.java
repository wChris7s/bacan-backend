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

@Service
public class ProductService implements ProductUseCase {

  private final ProductDatabase productDatabase;

  public ProductService(ProductDatabase productDatabase) {
    this.productDatabase = productDatabase;
  }

  @Override
  public Mono<Product> createProduct(Product product) {
    LocalDateTime now = ApplicationTimeUtil.getActualDateTime();
    return productDatabase.createProduct(
        product.withCreatedAt(now).withUpdatedAt(now)
    );
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
    return productDatabase.findProductById(productId);
  }

  @Override
  public Mono<Product> updateProduct(Long productId, Product product) {
    LocalDateTime now = ApplicationTimeUtil.getActualDateTime();
    return productDatabase.updateProduct(productId, product.withUpdatedAt(now));
  }

  @Override
  public Mono<Void> deleteProduct(Long productId) {
    return productDatabase.deleteById(productId);
  }
}

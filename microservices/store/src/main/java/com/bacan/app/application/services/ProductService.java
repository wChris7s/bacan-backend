package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.ProductUseCase;
import com.bacan.app.application.port.out.persistence.ProductDatabase;
import com.bacan.app.domain.models.product.Product;
import com.bacan.app.domain.queries.product.ProductQuery;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

  private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
  private final ProductDatabase productDatabase;

  @Override
  @Transactional
  public Mono<Product> createProduct(Product product) {
    return productDatabase.createProduct(product.withEnabled(true))
      .doOnSuccess(saved -> logger.info("Product '{}' created successfully.", saved.name()))
      .doOnError(e -> logger.error("Error creating product '{}': {}", product.name(), e.getMessage()));
  }

  @Override
  public Flux<Product> getAllProductsByQuery(ProductQuery productQuery) {
    return productDatabase.findAllProducts(productQuery)
      .doOnError(e -> logger.error("Error retrieving products: {}", e.getMessage()));
  }

  @Override
  public Mono<Long> countAllProductsByQuery(ProductQuery productQuery) {
    return productDatabase.countProductsByQuery(productQuery)
      .doOnError(e -> logger.error("Error counting products: {}", e.getMessage()));
  }

  @Override
  public Mono<Product> getProductByIdOrThrow(Long productId) {
    return productDatabase.findProductById(productId)
      .switchIfEmpty(Mono.defer(() -> {
        logger.error("Product with id {} not found.", productId);
        return Mono.error(new RuntimeException("Product not found"));
      }))
      .doOnError(e -> logger.error("Error retrieving product with id {}: {}", productId, e.getMessage()));
  }

  @Override
  @Transactional
  public Mono<Product> updateProduct(Long productId, Product product) {
    return productDatabase.findProductById(productId)
      .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Product not found"))))
      .map(existing ->
        existing
          .withName(product.name())
          .withPrice(product.price())
          .withStock(product.stock())
          .withDescription(product.description())
      )
      .flatMap(productDatabase::updateProduct)
      .doOnSuccess(updated -> logger.info("Product with id {} updated successfully.", productId))
      .doOnError(e -> logger.error("Error updating product with id {}: {}", productId, e.getMessage()));
  }
}
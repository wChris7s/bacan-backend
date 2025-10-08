package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductCategoryRepository {
  private final DatabaseClient databaseClient;

  public ProductCategoryRepository(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  public Mono<Void> save(Long productId, Long categoryId) {
    return databaseClient.sql("INSERT INTO store.product_category(product_id, category_id) VALUES (:productId, :categoryId)")
      .bind("productId", productId)
      .bind("categoryId", categoryId)
      .then();
  }

  public Mono<Void> delete(Long productId, Long categoryId) {
    return databaseClient.sql("DELETE FROM store.product_category WHERE product_id = :productId AND category_id = :categoryId")
      .bind("productId", productId)
      .bind("categoryId", categoryId)
      .then();
  }
}

package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProductCategoryEntity;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

public class ProductCategoryRepository {
  private final DatabaseClient databaseClient;

  public ProductCategoryRepository(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  public Mono<Void> save(ProductCategoryEntity productCategory) {
    return databaseClient.sql("INSERT INTO bacan.product_category(product_id, category_id) VALUES (:productId, :categoryId)")
      .bind("productId", productCategory.getProductId())
      .bind("categoryId", productCategory.getCategoryId())
      .then();
  }
}

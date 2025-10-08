package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.product.Product;
import com.bacan.app.domain.queries.product.ProductQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductDatabase {

  /* ============ Product ============ */

  Mono<Product> createProduct(Product product);

  Flux<Product> findAllProducts(ProductQuery query);

  Mono<Product> findProductById(Long productId);

  Mono<Long> countProductsByQuery(ProductQuery query);

  Mono<Product> updateProduct(Product product);

  /* ============ Product Category ============ */

  Mono<Void> createProductCategory(Long productId, Long categoryId);

  Mono<Void> deleteProductCategory(Long productId, Long categoryId);
}

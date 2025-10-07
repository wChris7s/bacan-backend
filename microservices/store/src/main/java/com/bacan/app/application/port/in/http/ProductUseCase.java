package com.bacan.app.application.port.in.http;

import com.bacan.app.domain.models.product.Product;
import com.bacan.app.domain.queries.product.ProductQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductUseCase {
  Mono<Product> createProduct(Product product);

  Flux<Product> getAllProductsByQuery(ProductQuery productQuery);

  Mono<Long> countAllProductsByQuery(ProductQuery productQuery);

  Mono<Product> getProductById(Long productId);
}

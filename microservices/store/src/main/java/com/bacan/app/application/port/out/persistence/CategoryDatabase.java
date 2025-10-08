package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.category.Category;
import com.bacan.app.domain.queries.category.CategoryQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryDatabase {
  Mono<Category> createCategory(Category category);

  Mono<Long> countAllByQuery(CategoryQuery query);

  Flux<Category> findAllByQuery(CategoryQuery query);

  Flux<Category> findByProductId(Long productId);

  Mono<Category> findById(Long categoryId);

  Mono<Category> updateCategory(Category category);
}

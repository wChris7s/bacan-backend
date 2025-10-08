package com.bacan.app.application.port.in.http;

import com.bacan.app.domain.models.category.Category;
import com.bacan.app.domain.queries.category.CategoryQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryUseCase {
  Mono<Long> countAllCategoriesByQuery(CategoryQuery categoryQuery);

  Flux<Category> getAllCategoriesByQuery(CategoryQuery categoryQuery);

  Flux<Category> getCategoriesByProductId(Long productId);

  Mono<Category> createCategory(Category category);

  Mono<Category> getCategoryById(Long categoryId);

  Mono<Category> updateCategory(Long categoryId, Category category);
}

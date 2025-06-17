package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.category.Category;
import com.bacan.app.domain.queries.category.CategoryQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryDatabase {
  Mono<Category> createCategory(Category category);

  Mono<Long> countCategoriesByQuery(CategoryQuery query);

  Flux<Category> findAllCategories(CategoryQuery query);

  Flux<Category> findCategoriesByProductId(String productId);
}

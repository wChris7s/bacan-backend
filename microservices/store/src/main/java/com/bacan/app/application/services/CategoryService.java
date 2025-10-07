package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.CategoryUseCase;
import com.bacan.app.application.port.out.persistence.CategoryDatabase;
import com.bacan.app.domain.models.category.Category;
import com.bacan.app.domain.queries.category.CategoryQuery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryService implements CategoryUseCase {

  private final CategoryDatabase categoryDatabase;

  public CategoryService(CategoryDatabase categoryDatabase) {
    this.categoryDatabase = categoryDatabase;
  }

  @Override
  public Mono<Long> countAllCategoriesByQuery(CategoryQuery categoryQuery) {
    return categoryDatabase.countCategoriesByQuery(categoryQuery);
  }

  @Override
  public Flux<Category> getAllCategoriesByQuery(CategoryQuery categoryQuery) {
    return categoryDatabase.findAllCategories(categoryQuery);
  }

  @Override
  public Flux<Category> getCategoriesByProductId(String productId) {
    return categoryDatabase.findCategoriesByProductId(productId);
  }

  @Override
  public Mono<Category> createCategory(Category category) {
    return null;
  }

  @Override
  public Mono<Category> getCategoryById(String categoryId) {
    return null;
  }
}

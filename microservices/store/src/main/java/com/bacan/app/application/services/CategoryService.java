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
  public Flux<Category> getCategoriesByProductId(Long productId) {
    // El puerto espera String:
    return categoryDatabase.findCategoriesByProductId(String.valueOf(productId));
  }

  @Override
  public Mono<Category> createCategory(Category category) {
    return categoryDatabase.createCategory(category);
  }

  @Override
  public Mono<Category> getCategoryById(Long categoryId) {
    // En el puerto real es findById(...)
    return categoryDatabase.findById(categoryId);
  }

  @Override
  public Mono<Category> updateCategory(Long categoryId, Category category) {
    // En el puerto real es update(...)
    return categoryDatabase.update(categoryId, category);
  }

  @Override
  public Mono<Void> deleteCategory(Long categoryId) {
    // En el puerto real es deleteById(...)
    return categoryDatabase.deleteById(categoryId);
  }
}

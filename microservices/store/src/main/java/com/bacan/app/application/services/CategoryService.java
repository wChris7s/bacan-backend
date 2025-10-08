package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.CategoryUseCase;
import com.bacan.app.application.port.out.persistence.CategoryDatabase;
import com.bacan.app.domain.models.category.Category;
import com.bacan.app.domain.queries.category.CategoryQuery;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryUseCase {

  private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

  private final CategoryDatabase categoryDatabase;

  @Override
  public Mono<Long> countAllCategoriesByQuery(CategoryQuery categoryQuery) {
    return categoryDatabase.countAllByQuery(categoryQuery)
      .doOnError(e -> logger.error("Error counting categories: {}", e.getMessage()));
  }

  @Override
  public Flux<Category> getAllCategoriesByQuery(CategoryQuery categoryQuery) {
    return categoryDatabase.findAllByQuery(categoryQuery)
      .doOnError(e -> logger.error("Error retrieving categories: {}", e.getMessage()));
  }

  @Override
  public Flux<Category> getCategoriesByProductId(Long productId) {
    return categoryDatabase.findByProductId(productId)
      .doOnError(e -> logger.error("Error retrieving categories for productId {}: {}", productId, e.getMessage()));
  }

  @Override
  @Transactional
  public Mono<Category> createCategory(Category category) {
    return categoryDatabase.createCategory(category)
      .doOnSuccess(saved -> logger.info("Category '{}' created successfully.", saved.name()))
      .doOnError(e -> logger.error("Error creating category '{}': {}", category.name(), e.getMessage()));
  }

  @Override
  public Mono<Category> getCategoryById(Long categoryId) {
    return categoryDatabase.findById(categoryId)
      .switchIfEmpty(Mono.defer(() -> {
        logger.error("Category with id {} not found.", categoryId);
        return Mono.error(new RuntimeException("Category not found"));
      }))
      .doOnError(e -> logger.error("Error retrieving category with id {}: {}", categoryId, e.getMessage()));
  }

  @Override
  @Transactional
  public Mono<Category> updateCategory(Long categoryId, Category category) {
    return categoryDatabase.findById(categoryId)
      .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Category not found"))))
      .map(existing -> existing.withName(category.name()))
      .flatMap(categoryDatabase::updateCategory)
      .doOnSuccess(updated -> logger.info("Category with id {} updated successfully.", categoryId))
      .doOnError(e -> logger.error("Error updating category with id {}: {}", categoryId, e.getMessage()));
  }
}

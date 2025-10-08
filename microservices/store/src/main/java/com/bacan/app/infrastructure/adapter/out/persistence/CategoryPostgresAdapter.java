package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.CategoryDatabase;
import com.bacan.app.domain.models.category.Category;
import com.bacan.app.domain.queries.category.CategoryQuery;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.CategoryEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class CategoryPostgresAdapter implements CategoryDatabase {

  private final CategoryRepository categoryRepository;

  public CategoryPostgresAdapter(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Mono<Category> createCategory(Category category) {
    CategoryEntity categoryEntity = CategoryEntityMapper.mapToEntity(category);
    return categoryRepository.save(categoryEntity)
        .map(CategoryEntityMapper::mapToModel);
  }

  @Override
  public Mono<Long> countCategoriesByQuery(CategoryQuery query) {
    return categoryRepository.countAllByNameContainingIgnoreCase(query.getName());
  }

  @Override
  public Flux<Category> findAllCategories(CategoryQuery query) {
    return categoryRepository.findAllByNameContainingIgnoreCase(query.getName(), query.getPageable())
        .map(CategoryEntityMapper::mapToModel);
  }

  @Override
  public Flux<Category> findCategoriesByProductId(String productId) {
    return categoryRepository.findAllByProductId(UUID.fromString(productId))
        .map(CategoryEntityMapper::mapToModel);
  }

  // ✅ NUEVOS: coinciden con CategoryDatabase actualizado

  @Override
  public Mono<Category> findById(Long categoryId) {
    return categoryRepository.findById(categoryId)
        .map(CategoryEntityMapper::mapToModel);
  }

  @Override
  public Mono<Category> update(Long categoryId, Category category) {
    CategoryEntity entity = CategoryEntityMapper.mapToEntity(category);
    // Si tu CategoryEntity es inmutable, crea por builder con id.
    entity.setId(categoryId);
    return categoryRepository.save(entity)
        .map(CategoryEntityMapper::mapToModel);
  }

  @Override
  public Mono<Void> deleteById(Long categoryId) {
    return categoryRepository.deleteById(categoryId);
  }
}

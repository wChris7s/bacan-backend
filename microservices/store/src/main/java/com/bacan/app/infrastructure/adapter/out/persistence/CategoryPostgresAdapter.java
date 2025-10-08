package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.CategoryDatabase;
import com.bacan.app.domain.models.category.Category;
import com.bacan.app.domain.queries.category.CategoryQuery;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.CategoryEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoryPostgresAdapter implements CategoryDatabase {

  private final CategoryRepository categoryRepository;

  static final CategoryEntityMapper categoryEntityMapper = Mappers.getMapper(CategoryEntityMapper.class);

  @Override
  public Mono<Category> createCategory(Category category) {
    CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
    return categoryRepository.save(categoryEntity)
      .map(categoryEntityMapper::toModel);
  }

  @Override
  public Mono<Long> countAllByQuery(CategoryQuery query) {
    return categoryRepository.countAllByNameContainingIgnoreCase(query.getName());
  }

  @Override
  public Flux<Category> findAllByQuery(CategoryQuery query) {
    return categoryRepository.findAllByNameContainingIgnoreCase(query.getName(), query.getPageable())
      .map(categoryEntityMapper::toModel);
  }

  @Override
  public Flux<Category> findByProductId(Long productId) {
    return categoryRepository.findAllByProductId(productId)
      .map(categoryEntityMapper::toModel);
  }

  @Override
  public Mono<Category> findById(Long categoryId) {
    return categoryRepository.findById(categoryId)
      .map(categoryEntityMapper::toModel);
  }

  @Override
  public Mono<Category> updateCategory(Category category) {
    return this.createCategory(category);
  }
}

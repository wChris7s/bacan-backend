package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.category.Category;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryEntityMapper {
  public static CategoryEntity mapToEntity(Category category) {
    return CategoryEntity.builder()
      .id(category.id())
      .name(category.name())
      .createdAt(category.createdAt())
      .updatedAt(category.updatedAt())
      .enabled(category.enabled())
      .build();
  }

  public static Category mapToModel(CategoryEntity category) {
    return Category.builder()
      .id(category.getId())
      .name(category.getName())
      .createdAt(category.getCreatedAt())
      .updatedAt(category.getUpdatedAt())
      .enabled(category.getEnabled())
      .build();
  }
}
